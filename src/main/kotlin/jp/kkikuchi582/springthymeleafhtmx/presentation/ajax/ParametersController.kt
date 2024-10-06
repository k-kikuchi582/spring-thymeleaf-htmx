package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.annotation.SessionScope
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Controller
@RequestMapping("/ajax/parameters")
class ParametersController(private val uploadFileContainer: UploadFileContainer) {

    @GetMapping
    fun get(model: Model): String {
        model.addAttribute("isUploaded", uploadFileContainer.isUploaded())
        return "ajax/parameters"
    }

    @GetMapping("element-has-value")
    fun modifierChanged(@RequestParam("element-has-value") value: String, model: Model): String {
        model.addAttribute("value", value)
        return "ajax/parameters :: element-has-value"
    }

    @PostMapping("form")
    fun form(
        @RequestParam("include", required = false) yourName: String?,
        @RequestParam("fruit", required = false) fruits: List<String>?,
        @RequestParam("pet", required = false) pets: List<String>?,
        model: Model,
    ): String {
        yourName?.let { model.addAttribute("yourName", it) }
        fruits?.joinToString(",")?.let { model.addAttribute("fruits", it) }
        pets?.joinToString(",")?.let { model.addAttribute("pets", it) }
        return "ajax/parameters :: show-form-data"
    }

    @PostMapping("params")
    fun params(
        @RequestParam("your-name-params", required = false) yourName: String?,
        @RequestParam("fruit-params", required = false) fruits: List<String>?,
        @RequestParam("pet-params", required = false) pets: List<String>?,
        model: Model,
    ): String {
        yourName?.let { model.addAttribute("yourName", it) }
        fruits?.joinToString(",")?.let { model.addAttribute("fruits", it) }
        pets?.joinToString(",")?.let { model.addAttribute("pets", it) }
        return "ajax/parameters :: show-form-data-params"
    }

    @PostMapping("include")
    fun include(
        @RequestParam("include") value: String,
        model: Model,
    ): String {
        model.addAttribute( "value", value )
        return "ajax/parameters :: include"
    }

    @PostMapping("file")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile,
        model: Model,
    ): String {
        uploadFileContainer.set(file)
        model.addAttribute( "isUploaded", uploadFileContainer.isUploaded() )

        return "ajax/parameters :: uploaded-file-viewer"
    }

    @GetMapping("file/uploaded")
    fun getUploadedFile(): ResponseEntity<Any> {
        var uploadFile = uploadFileContainer.file
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok()
            .contentType(uploadFile.contentType)
            .contentLength(uploadFile.getContentLength())
            .body(uploadFile.content)
    }
}

@Component
@SessionScope
class UploadFileContainer(internal var file: UploadFile? = null) {
    fun isUploaded() = file != null

    fun set( multiPartFile: MultipartFile ) {
        file = UploadFile.from(multiPartFile)
    }
}

class UploadFile(
    val fileName: String,
    val contentType: MediaType,
    val content: ByteArray,
) {
    fun getContentLength(): Long = content.size.toLong()

    companion object {
        fun from( multipartFile: MultipartFile ): UploadFile {
            val fileName = multipartFile.originalFilename ?: multipartFile.name
            val contentType = MediaType.parseMediaType(multipartFile.contentType ?: Files.probeContentType(Paths.get(fileName)))
            val content = multipartFile.bytes
            return UploadFile(fileName, contentType, content)
        }
    }
}
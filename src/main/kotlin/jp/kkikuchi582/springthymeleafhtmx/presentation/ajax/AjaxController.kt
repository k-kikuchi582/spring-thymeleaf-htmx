package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/ajax")
class AjaxController {
    @GetMapping
    fun page(): String {
        return "ajax/index"
    }

    @RequestMapping( "/ajax", method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    fun get( request: HttpServletRequest, model: Model ): String {
        model.addAttribute("method", request.method.uppercase() )
        model.addAttribute("useragent", request.getHeader( "User-Agent" ))
        model.addAttribute("host", request.getHeader("Host"))
        return "ajax/index :: ajax"
    }
}

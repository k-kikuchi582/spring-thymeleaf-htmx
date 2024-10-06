package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/ajax/indicator")
class IndicatorController {
    @GetMapping
    fun page(): String {
        return "ajax/indicator"
    }

    @GetMapping( "/to-after" )
    fun toAfter(): String {
        Thread.sleep(3000)
        return "ajax/indicator :: after"
    }

    @GetMapping( "/to-before" )
    fun toBefore(): String {
        Thread.sleep(3000)
        return "ajax/indicator :: before"
    }
}

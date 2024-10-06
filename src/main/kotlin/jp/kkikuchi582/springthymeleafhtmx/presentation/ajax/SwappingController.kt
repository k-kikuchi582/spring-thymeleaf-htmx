package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping( "/ajax/swapping" )
class SwappingController {
    @GetMapping
    fun page() = "ajax/swapping"

    @GetMapping( "/innerHTML" )
    fun innerHTML() = "ajax/swapping :: innerHTML"

    @GetMapping( "/outerHTML" )
    fun outerHTML() = "ajax/swapping :: outerHTML"

    @GetMapping( "/beforebegin" )
    fun beforeBegin() = "ajax/swapping :: beforebegin"

    @GetMapping( "/afterbegin" )
    fun afterBegin() = "ajax/swapping :: afterbegin"

    @GetMapping( "/beforeend" )
    fun beforeEnd() = "ajax/swapping :: beforeend"

    @GetMapping( "/afterend" )
    fun afterEnd() = "ajax/swapping :: afterend"

    @GetMapping( "/delete" )
    fun delete() = "ajax/swapping :: innerHTML"

    @GetMapping( "/none" )
    fun none() = "ajax/swapping :: innerHTML"
}
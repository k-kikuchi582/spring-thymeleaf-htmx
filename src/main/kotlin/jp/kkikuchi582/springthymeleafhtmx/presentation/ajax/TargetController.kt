package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping( "/ajax/target" )
class TargetController {
    @GetMapping
    fun page(): String = "ajax/target"

    @GetMapping( "/none" )
    fun none(): String = "ajax/target :: none"

    @GetMapping( "/this" )
    fun _this(): String = "ajax/target :: this"

    @GetMapping( "/plain" )
    fun plain(): String = "ajax/target :: plain"

    @GetMapping( "/closest" )
    fun closest(): String = "ajax/target :: closest"

    @GetMapping( "/next" )
    fun next(): String = "ajax/target :: next"

    @GetMapping( "/previous" )
    fun previous(): String = "ajax/target :: previous"

    @GetMapping( "/find" )
    fun find(): String = "ajax/target :: find"
}
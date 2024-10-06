package jp.kkikuchi582.springthymeleafhtmx.presentation.ajax

import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.annotation.SessionScope
import java.util.*

@Controller
@RequestMapping("/ajax/trigger")
class TriggerController(private val loadPollingState: LoadPollingState) {

    @GetMapping
    fun get(): String {
        loadPollingState.reset()
        return "ajax/trigger"
    }

    @GetMapping("trigger-click")
    fun triggerClick() = "ajax/trigger :: trigger-click"

    @GetMapping("trigger-change")
    fun triggerChange(@RequestParam("trigger-change") fruit: String, model: Model): String {
        model.addAttribute("fruit", fruit)
        return "ajax/trigger :: trigger-change"
    }

    @GetMapping("modifier-changed")
    fun modifierChanged(@RequestParam("modifier-changed") value: String, model: Model): String {
        model.addAttribute("value", value);
        return "ajax/trigger :: modifier-changed";
    }

    @GetMapping("special-event-load")
    fun specialEventLoad() = "ajax/trigger :: special-event-load"


    @GetMapping("polling")
    fun polling(model: Model): String {
        model.addAttribute("datetime", Date())
        return "ajax/trigger :: polling"
    }

    @GetMapping("load-polling")
    fun loadPolling(model: Model): String {
        val progressValue = loadPollingState.progress()
        if (loadPollingState.isFinished()) {
            return "ajax/trigger :: load-polling-completed"
        }
        model.addAttribute("value", progressValue)
        return "ajax/trigger :: load-polling"
    }
}

@Component
@SessionScope
class LoadPollingState {
    internal var value: Int = 0

    fun isFinished() = value >= 100

    fun progress(): Int {
        if (isFinished()) {
            return value;
        }
        value += 10
        return value;
    }

    fun reset() {
        value = 0
    }
}
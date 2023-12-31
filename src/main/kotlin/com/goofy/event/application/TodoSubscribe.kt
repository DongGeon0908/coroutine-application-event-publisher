package com.goofy.event.application

import com.goofy.event.extension.toJson
import com.goofy.event.pubsub.model.TodoEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TodoSubscribe {
    private val logger = mu.KotlinLogging.logger { }

    @EventListener
    fun handle1(event: TodoEvent) {
        logger.info { event.toJson() }
    }
}

package com.goofy.event.application

import com.goofy.event.extension.toJson
import com.goofy.event.pubsub.model.TodoEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class TodoListener {
    private val logger = mu.KotlinLogging.logger { }

    @EventListener
    fun handle1(event: TodoEvent) {
        logger.info { "handle1 ${event.toJson()}" }
    }

    @EventListener
    fun handle2(event: TodoEvent) {
        logger.info { "handle2 ${event.toJson()}" }
    }

    @TransactionalEventListener
    fun handle3(event: TodoEvent) {
        logger.info { "handle3 ${event.toJson()}" }
    }
}

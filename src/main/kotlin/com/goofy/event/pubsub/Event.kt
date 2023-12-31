package com.goofy.event.pubsub

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDateTime

interface Event {
    val event: String
    val publishedAt: LocalDateTime
}

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publish(event: Event) {
        applicationEventPublisher.publishEvent(event)
    }

    fun multiPublish(events: List<Event>) {
        events.forEach { event -> publish(event) }
    }

    suspend fun publishWithContext(event: Event) {
        CoroutineScope(Dispatchers.IO).launch {
            publish(event)
        }
    }

    suspend fun multiPublishWithContext(events: List<Event>) {
        events.forEach { event ->
            CoroutineScope(Dispatchers.IO).launch {
                publish(event)
            }
        }
    }
}

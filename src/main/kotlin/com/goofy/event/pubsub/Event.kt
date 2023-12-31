package com.goofy.event.pubsub

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDateTime

sealed class Event<EVENT_DATA>(
    val event: String,
    val data: EVENT_DATA,
    val publishedAt: LocalDateTime = LocalDateTime.now()
)

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun <EVENT_DATA> publish(event: Event<EVENT_DATA>) {
        applicationEventPublisher.publishEvent(event)
    }


    fun <EVENT_DATA> multiPublish(events: List<Event<EVENT_DATA>>) {
        events.forEach { event -> publish(event) }
    }

    suspend fun <EVENT_DATA> publishWithContext(event: Event<EVENT_DATA>) {
        CoroutineScope(Dispatchers.IO).launch {
            publish(event)
        }
    }

    suspend fun <EVENT_DATA> multiPublishWithContext(events: List<Event<EVENT_DATA>>) {
        events.forEach { event ->
            CoroutineScope(Dispatchers.IO).launch {
                publish(event)
            }
        }
    }
}

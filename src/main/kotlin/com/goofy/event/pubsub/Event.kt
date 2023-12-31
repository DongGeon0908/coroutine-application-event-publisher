package com.goofy.event.pubsub

import java.time.LocalDateTime

sealed class Event<EVENT_DATA>(
    val data: EVENT_DATA,
    val publishedAt: LocalDateTime = LocalDateTime.now()
)


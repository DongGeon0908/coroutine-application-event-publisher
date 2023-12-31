package com.goofy.event.pubsub.model

import com.goofy.event.domain.vo.TodoCategory
import com.goofy.event.pubsub.Event
import java.time.LocalDateTime

data class TodoEvent(
    override val event: String,
    override val publishedAt: LocalDateTime,
    val id: Long,
    val title: String,
    val content: String,
    val category: TodoCategory,
    val active: Boolean
) : Event

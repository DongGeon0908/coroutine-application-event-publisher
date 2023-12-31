package com.goofy.event.application

import com.goofy.event.infrastructure.TodoRepository
import com.goofy.event.pubsub.model.TodoEvent
import com.goofy.event.model.response.TodoResponse
import com.goofy.event.pubsub.EventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val eventPublisher: EventPublisher
) {
    suspend fun get(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id)
            ?: throw RuntimeException("데이터가 없음")

        TodoEvent(
            event = "TODO_EVENT",
            publishedAt = LocalDateTime.now(),
            id = todo.id,
            title = todo.title,
            content = todo.content,
            category = todo.category,
            active = todo.active
        ).run { eventPublisher.publish(this) }

        return TodoResponse.from(todo)
    }
}

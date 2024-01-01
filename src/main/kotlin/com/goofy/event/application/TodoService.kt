package com.goofy.event.application

import com.goofy.event.domain.Todo
import com.goofy.event.infrastructure.TodoRepository
import com.goofy.event.model.request.TodoRequest
import com.goofy.event.model.response.TodoResponse
import com.goofy.event.pubsub.EventPublisher
import com.goofy.event.pubsub.model.TodoEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val eventPublisher: EventPublisher
) {
    suspend fun getV1(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id)
            ?: throw RuntimeException("데이터가 없음")

        /** 기본 리스너 */
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

    suspend fun getV2(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id)
            ?: throw RuntimeException("데이터가 없음")

        /** 기본 리스너 */
        TodoEvent(
            event = "TODO_EVENT",
            publishedAt = LocalDateTime.now(),
            id = todo.id,
            title = todo.title,
            content = todo.content,
            category = todo.category,
            active = todo.active
        ).run { eventPublisher.publishWithContext(this) }

        return TodoResponse.from(todo)
    }

    @Transactional
    suspend fun createV1(request: TodoRequest): TodoResponse {
        val createdTodo = Todo(
            title = request.title,
            content = request.content,
            category = request.category,
            active = request.active
        ).run { todoRepository.save(this) }

        /** 기본 리스너 */
        TodoEvent(
            event = "TODO_EVENT",
            publishedAt = LocalDateTime.now(),
            id = createdTodo.id,
            title = createdTodo.title,
            content = createdTodo.content,
            category = createdTodo.category,
            active = createdTodo.active
        ).run { eventPublisher.publishWithContext(this) }

        return TodoResponse.from(createdTodo)
    }
}

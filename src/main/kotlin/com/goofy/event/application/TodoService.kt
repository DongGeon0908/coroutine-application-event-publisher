package com.goofy.event.application

import com.goofy.event.infrastructure.TodoRepository
import com.goofy.event.model.response.TodoResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    suspend fun get(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id)
            ?: throw RuntimeException("데이터가 없음")

        return TodoResponse.from(todo)
    }
}

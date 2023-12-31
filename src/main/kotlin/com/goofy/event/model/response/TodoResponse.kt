package com.goofy.event.model.response

import com.goofy.event.domain.Todo
import com.goofy.event.domain.vo.TodoCategory

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: TodoCategory,
    val active: Boolean
) {
    companion object {
        fun from(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id,
                title = todo.title,
                content = todo.content,
                category = todo.category,
                active = todo.active
            )
        }
    }
}

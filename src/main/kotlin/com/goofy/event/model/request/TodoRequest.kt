package com.goofy.event.model.request

import com.goofy.event.domain.vo.TodoCategory

data class TodoRequest(
    val title: String = "todo title",
    val content: String = "todo content",
    val category: TodoCategory = TodoCategory.STUDY,
    val active: Boolean = true
)

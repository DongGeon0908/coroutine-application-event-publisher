package com.goofy.event.presentation

import com.goofy.event.application.TodoService
import com.goofy.event.extension.wrapOk
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/todo")
class TodoResource(
    private val todoService: TodoService,
) {
    @GetMapping("/{id}")
    suspend fun get(
        @PathVariable id: Long
    ) = todoService.get(id).wrapOk()
}

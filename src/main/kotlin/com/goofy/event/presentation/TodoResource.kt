package com.goofy.event.presentation

import com.goofy.event.application.TodoService
import com.goofy.event.extension.wrapCreated
import com.goofy.event.extension.wrapOk
import com.goofy.event.model.request.TodoRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class TodoResource(
    private val todoService: TodoService,
) {
    @GetMapping("/api/v1/todo/{id}")
    suspend fun getV1(
        @PathVariable id: Long
    ) = todoService.getV1(id).wrapOk()

    @GetMapping("/api/v2/todo/{id}")
    suspend fun getV2(
        @PathVariable id: Long
    ) = todoService.getV2(id).wrapOk()

    @PostMapping("/api/v1/todo/{id}")
    suspend fun createV1(
        @RequestBody request: TodoRequest
    ) = todoService.createV1(request).wrapCreated()
}

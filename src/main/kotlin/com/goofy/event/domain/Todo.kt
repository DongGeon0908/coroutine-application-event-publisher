package com.goofy.event.domain

import com.goofy.event.domain.vo.TodoCategory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "todo")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = -1,

    var title: String,

    var content: String,

    @Enumerated(value = EnumType.STRING)
    var category: TodoCategory,

    @Column(name = "is_active")
    var active: Boolean = true
) : BaseEntity() {
    fun update(title: String, content: String, category: TodoCategory) {
        this.title = title
        this.content = content
        this.category = category
    }

    fun changedActive(isActive: Boolean) {
        this.active = isActive
    }
}

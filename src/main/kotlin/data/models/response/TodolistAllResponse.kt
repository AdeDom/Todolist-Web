package data.models.response

import data.models.model.TodolistAll
import kotlinx.serialization.Serializable

@Serializable
data class TodolistAllResponse(
    var success: Boolean = false,
    var message: String? = null,
    var todolistAll: List<TodolistAll> = emptyList(),
)

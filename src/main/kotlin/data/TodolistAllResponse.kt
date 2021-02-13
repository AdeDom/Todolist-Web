package data

import kotlinx.serialization.Serializable

@Serializable
data class TodolistAllResponse(
    var success: Boolean = false,
    var message: String? = null,
    var todolistAll: List<TodolistAll> = emptyList(),
)

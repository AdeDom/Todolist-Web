package data.models.request

import kotlinx.serialization.Serializable

@Serializable
data class AddTodolistRequest(
    val todolistId: String? = null,
    val title: String? = null,
    val content: String? = null,
)

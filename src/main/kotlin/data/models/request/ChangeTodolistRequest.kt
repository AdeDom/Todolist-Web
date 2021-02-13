package data.models.request

import kotlinx.serialization.Serializable

@Serializable
data class ChangeTodolistRequest(
    val todolistId: String? = null,
    val title: String? = null,
    val content: String? = null,
)

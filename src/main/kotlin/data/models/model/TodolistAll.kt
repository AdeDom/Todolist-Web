package data.models.model

import kotlinx.serialization.Serializable

@Serializable
data class TodolistAll(
    val todolistId: String,
    val userId: String,
    val title: String,
    val content: String,
    val createdLong: Long,
    val updatedLong: Long?,
    val createdString: String,
    val updatedString: String?,
    val isShow: Boolean,
)

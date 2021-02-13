package data.network.api

import data.models.request.AddTodolistRequest
import data.models.response.BaseResponse
import data.models.response.TodolistAllResponse
import data.network.client.client
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TodolistApi {

    suspend fun fetchTodolistAll(): TodolistAllResponse {
        return client.get("${BASE_URL}api/todolist/todolist-all")
    }

    suspend fun addTodolist(addTodolistRequest: AddTodolistRequest): BaseResponse {
        return client.post("${BASE_URL}api/todolist/add-todolist") {
            body = TextContent(
                text = Json.encodeToString(addTodolistRequest),
                contentType = ContentType.Application.Json
            )
        }
    }

    companion object {
        const val BASE_URL = "https://todolist-server-94.herokuapp.com/"
//        const val BASE_URL = "http://192.168.43.22:8080/"
    }

}

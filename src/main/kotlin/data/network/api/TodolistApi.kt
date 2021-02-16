package data.network.api

import data.models.request.AddTodolistRequest
import data.models.request.ChangeTodolistRequest
import data.models.response.BaseResponse
import data.models.response.TodolistAllResponse
import data.network.client.client
import io.ktor.client.request.*
import io.ktor.http.*

class TodolistApi {

    suspend fun fetchTodolistAll(): TodolistAllResponse {
        return client.get("${BASE_URL}api/todolist/todolist-all")
    }

    suspend fun addTodolist(addTodolistRequest: AddTodolistRequest): BaseResponse {
        return client.post("${BASE_URL}api/todolist/add-todolist") {
            contentType(ContentType.Application.Json)
            body = addTodolistRequest
        }
    }

    suspend fun changeTodolist(changeTodolistRequest: ChangeTodolistRequest): BaseResponse {
        return client.put("${BASE_URL}api/todolist/change-todolist") {
            contentType(ContentType.Application.Json)
            body = changeTodolistRequest
        }
    }

    suspend fun removeTodolist(todolistId: String): BaseResponse {
        return client.delete("${BASE_URL}api/todolist/remove-todolist/$todolistId")
    }

    companion object {
        const val BASE_URL = "https://todolist-server-94.herokuapp.com/"
//        const val BASE_URL = "http://192.168.43.22:8080/"
    }

}

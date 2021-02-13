package data.network.api

import data.models.response.TodolistAllResponse
import data.network.client.client
import io.ktor.client.request.*

class TodolistApi {

    suspend fun getTodolistAll(): TodolistAllResponse {
        return client.get("${BASE_URL}api/todolist/todolist-all")
    }

    companion object {
        const val BASE_URL = "https://todolist-server-94.herokuapp.com/"
//        const val BASE_URL = "http://192.168.43.22:8080/"
    }

}

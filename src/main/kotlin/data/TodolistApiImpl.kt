package data

import data.TodolistApi.Companion.BASE_URL
import io.ktor.client.request.*

class TodolistApiImpl : TodolistApi {

    override suspend fun getTodolistAll(): TodolistAllResponse {
        return client.get("${BASE_URL}api/todolist/todolist-all")
    }

}

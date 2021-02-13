package data

interface TodolistApi {

    suspend fun getTodolistAll(): TodolistAllResponse

    companion object {
        const val BASE_URL = "https://todolist-server-94.herokuapp.com/"
//        const val BASE_URL = "http://192.168.43.22:8080/"
    }

}

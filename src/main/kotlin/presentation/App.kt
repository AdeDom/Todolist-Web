package presentation

import data.models.model.TodolistAll
import data.models.request.AddTodolistRequest
import data.network.api.TodolistApi
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import utils.Utils
import kotlin.js.Date

interface AppProps : RProps

data class AppState(
    var todolistAll: List<TodolistAll>,
    var todolistDetail: TodolistAll?,
    var title: String,
    var content: String,
) : RState

class App(props: AppProps) : RComponent<AppProps, AppState>(props) {

    private val scope = MainScope()
    private val api = TodolistApi()

    init {
        fetchTodolistAll()
    }

    private fun fetchTodolistAll() {
        scope.launch {
            val response = api.fetchTodolistAll()
            if (response.success) {
                setState {
                    todolistAll = response.todolistAll
                }
            }
        }
    }

    override fun AppState.init(props: AppProps) {
        todolistAll = emptyList()
        title = ""
        content = ""
    }

    override fun RBuilder.render() {
        th {
            h1 {
                +"Hello todolist"
            }

            input {
                attrs {
                    type = InputType.text
                    value = state.title
                    placeholder = "Title"
                    onChangeFunction = { event ->
                        setState(
                            AppState(
                                todolistAll = state.todolistAll,
                                todolistDetail = state.todolistDetail,
                                title = (event.target as HTMLInputElement).value,
                                content = state.content
                            )
                        )
                    }
                }
            }

            input {
                attrs {
                    type = InputType.text
                    value = state.content
                    placeholder = "Content"
                    onChangeFunction = { event ->
                        setState(
                            AppState(
                                todolistAll = state.todolistAll,
                                todolistDetail = state.todolistDetail,
                                title = state.title,
                                content = (event.target as HTMLInputElement).value
                            )
                        )
                    }
                }
            }

            button {
                +"Add todolist"
                attrs {
                    onClickFunction = {
                        when {
                            state.title.isBlank() -> window.alert("Please enter title")
                            state.content.isBlank() -> window.alert("Please enter content")
                            else -> {
                                scope.launch {
                                    if (window.confirm("Add todolist")) {
                                        val request = AddTodolistRequest(
                                            todolistId = Date().getTime().toString(),
                                            title = state.title,
                                            content = state.content,
                                        )
                                        setState {
                                            title = ""
                                            content = ""
                                        }
                                        val response = api.addTodolist(request)
                                        if (response.success) {
                                            fetchTodolistAll()
                                            window.alert("OK : ${response.message}")
                                        } else {
                                            window.alert("Error : ${response.message}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            td {
                state.todolistAll.forEach { todolistAll ->
                    button {
                        p { +"todolistId : ${todolistAll.todolistId}" }
                        p { +"title : ${todolistAll.title}" }
                        p { +"content : ${todolistAll.content}" }

                        if (todolistAll.updatedLong == null) {
                            p { +Utils.getDateTimeFormatString(todolistAll.createdLong) }
                        } else {
                            p { +Utils.getDateTimeFormatString(todolistAll.updatedLong) }
                        }

                        attrs {
                            onClickFunction = {
                                setState {
                                    todolistDetail = todolistAll
                                }
                            }
                        }
                    }
                    button {
                        +"x"
                        attrs {
                            onClickFunction = {
                                scope.launch {
                                    if (window.confirm("Remove todolist title : ${todolistAll.title}")) {
                                        val response = api.removeTodolist(todolistAll.todolistId)
                                        if (response.success) {
                                            fetchTodolistAll()
                                            window.alert("OK : ${response.message}")
                                        } else {
                                            window.alert("Error : ${response.message}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                    br { }
                    br { }
                }
            }
            td {
                state.todolistDetail?.let { todolistAll ->
                    p { +"todolistId : ${todolistAll.todolistId}" }
                    p { +"title : ${todolistAll.title}" }
                    p { +"content : ${todolistAll.content}" }

                    if (todolistAll.updatedLong == null) {
                        p { +Utils.getDateTimeFormatString(todolistAll.createdLong) }
                    } else {
                        p { +Utils.getDateTimeFormatString(todolistAll.updatedLong) }
                    }
                }
            }
        }
    }

}

fun RBuilder.app() = child(App::class) {

}

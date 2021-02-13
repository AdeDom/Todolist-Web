package presentation

import data.models.model.TodolistAll
import data.network.api.TodolistApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.h1
import react.dom.p
import styled.css
import styled.styledDiv

interface AppProps : RProps

interface AppState : RState {
    var todolistAll: List<TodolistAll>
}

class App(props: AppProps) : RComponent<AppProps, AppState>(props) {

    init {
        MainScope().launch {
            val response = TodolistApi().getTodolistAll()
            if (response.success) {
                setState {
                    todolistAll = response.todolistAll
                }
            }
        }
    }

    override fun AppState.init(props: AppProps) {
        todolistAll = emptyList()
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
            }
            h1 {
                +"Hello todolist"
            }

            state.todolistAll.forEach {
                p {
                    +"${it.title}\t${it.content}"
                }
            }
        }
    }

}

fun RBuilder.app() = child(App::class) {

}

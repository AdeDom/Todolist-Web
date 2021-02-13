import kotlinx.browser.document
import kotlinx.browser.window
import presentation.app
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            app()
        }
    }
}

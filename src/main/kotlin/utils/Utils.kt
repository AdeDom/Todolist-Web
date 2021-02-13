package utils

import kotlin.js.Date

object Utils {

    fun getDateTimeFormatString(dateTimeLong: Long): String {
        val date = Date(dateTimeLong).getDate()
        val month = Date(dateTimeLong).getMonth()
        val fullYear = Date(dateTimeLong).getFullYear()
        val hours = Date(dateTimeLong).getHours()
        val minutes = Date(dateTimeLong).getMinutes()
        return "$date/${month.plus(1)}/$fullYear $hours:$minutes"
    }

}

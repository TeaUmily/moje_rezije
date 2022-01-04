package hr.tumiljanovic.mojerezije.common.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun getCurrentDateByFormat(format: String): String {
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat(format,  Locale.ENGLISH)
            return formatter.format(date)
        }

        fun convertStringToDateByFormat(format: String, dateString: String): Date? {
            val parser =  SimpleDateFormat(format, Locale.ENGLISH)
            return try {
                parser.parse(dateString)
            } catch (e: ParseException){
                Log.e("DateUtils", "${e.message}, date: $dateString")
                null
            }
        }

        fun dateToFormat(format: String, date: Date): String {
            val formatter = SimpleDateFormat(format,  Locale.ENGLISH)
            return formatter.format(date)
        }

        fun getMonthFromDate(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.MONTH) + 1
        }

        fun getDayFromDate(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

        fun getYearFromDate(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.YEAR)
        }
    }
}
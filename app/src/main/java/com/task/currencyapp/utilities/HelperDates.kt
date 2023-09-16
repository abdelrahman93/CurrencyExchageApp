package com.task.currencyapp.utilities

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HelperDates {

    fun getCurrentDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(cal.time)
    }

    fun getYesterdayDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -2)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(cal.time)
    }

    fun getTwoDaysAgoDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -3)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(cal.time)
    }
}

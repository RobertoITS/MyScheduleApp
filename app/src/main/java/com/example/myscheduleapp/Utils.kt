package com.example.myscheduleapp

import android.annotation.SuppressLint
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object Utils {

    var selectedDate: LocalDate? = null

    @SuppressLint("NewApi")
    fun formattedDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        return date.format(formatter)
    }

    @SuppressLint("NewApi")
    fun formattedTime(time: LocalTime): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        return time.format(formatter)
    }

    @SuppressLint("NewApi")
    fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @SuppressLint("NewApi")
    fun daysInMonthArray(date: LocalDate?): ArrayList<LocalDate?> {
        val daysInMonthArray: ArrayList<LocalDate?> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) daysInMonthArray.add(null) else daysInMonthArray.add(
                selectedDate?.year
                    ?.let { LocalDate.of(it, selectedDate?.month, i - dayOfWeek) })
        }
        return daysInMonthArray
    }

    @SuppressLint("NewApi")
    fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate?>? {
        val days: ArrayList<LocalDate?> = ArrayList()
        var current: LocalDate? = sundayForDate(selectedDate)
        val endDate: LocalDate = current!!.plusWeeks(1)
        while (current!!.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    @SuppressLint("NewApi")
    private fun sundayForDate(currentDate: LocalDate): LocalDate? {
        var current: LocalDate = currentDate
        val oneWeekAgo: LocalDate = current.minusWeeks(1)
        while (current.isAfter(oneWeekAgo)) {
            if (current.dayOfWeek === DayOfWeek.SUNDAY) return current
            current = current.minusDays(1)
        }
        return null
    }
}
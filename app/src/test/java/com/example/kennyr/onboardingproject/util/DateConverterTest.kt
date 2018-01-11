package com.example.kennyr.onboardingproject.util

import org.junit.Before
import org.junit.Test
import java.util.*

class DateConverterTest {

    private val calendar = Calendar.getInstance()
    private lateinit var date : Date

    @Before
    fun setup() {
        calendar.set(2018, 0, 2)
        date = calendar.time
    }

    @Test
    fun testDateToMonthYearLong() {
        assert(DateConverter.dateToMonthDayYearLong(calendar.time) == "January 2, 2018")
    }

    @Test
    fun testDateToMonthYearShort() {
        assert(DateConverter.dateToMonthDayYearShort(calendar.time) == "Jan/02/18")
    }

    @Test
    fun testDateFromMonthYearLong() {
        assert(date == DateConverter.dateFromMonthDayYearLong("January 2, 2018"))
    }

    @Test
    fun testDateFromMonthYearShort() {
        assert(date == DateConverter.dateFromMonthDayYearShort("Jan/02/18"))
    }
}
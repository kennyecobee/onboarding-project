package com.example.kennyr.onboardingproject.util

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class DateConverterTest {

    private val calendar = Calendar.getInstance()
    private lateinit var date : Date

    @Before
    fun setup() {
        calendar.set(2018, 0, 2)
        date = GregorianCalendar(2018, Calendar.JANUARY, 2).time
    }

    @Test
    fun testDateToMonthYearLong() {
        assertTrue(DateConverter.dateToMonthDayYearLong(calendar.time) == "January 02, 2018")
    }

    @Test
    fun testDateToMonthYearShort() {
        assertTrue(DateConverter.dateToMonthDayYearShort(calendar.time) == "Jan/02/18")
    }

    @Test
    fun testDateFromMonthYearLong() {
        assertTrue(date == DateConverter.dateFromMonthDayYearLong("January 02, 2018"))
    }

    @Test
    fun testDateFromMonthYearShort() {
        assertTrue(date == DateConverter.dateFromMonthDayYearShort("Jan/02/18"))
    }
}
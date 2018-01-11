package com.example.kennyr.onboardingproject.util

import junit.framework.Assert.assertTrue
import org.junit.Test

class TextUtilsTest {

    @Test
    fun testNullText() {
        assertTrue(TextUtils.isEmpty(null))
    }

    @Test
    fun testEmptyText() {
        assertTrue(TextUtils.isEmpty(""))
    }

    @Test
    fun testNonEmptyText() {
        assertTrue(!TextUtils.isEmpty("Text"))
    }
}
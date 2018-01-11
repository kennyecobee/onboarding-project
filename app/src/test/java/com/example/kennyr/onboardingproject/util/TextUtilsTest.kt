package com.example.kennyr.onboardingproject.util

import org.junit.Test

class TextUtilsTest {

    @Test
    fun testNullText() {
        assert(TextUtils.isEmpty(null))
    }

    @Test
    fun testEmptyText() {
        assert(TextUtils.isEmpty(""))
    }

    @Test
    fun testNonEmptyText() {
        assert(!TextUtils.isEmpty("Text"))
    }
}
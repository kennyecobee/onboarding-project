package com.example.kennyr.onboardingproject.tasks

import org.junit.Test
import java.util.*

class TaskDataSourceTest {

    private val dataSource = TaskDataSource.getInstance()
    private val task = Task("title", "description", Date())

    @Test
    fun `adding the first task and retrieving it`() {
        dataSource.add(task)
        assert(dataSource.get(0) == task)
    }

    @Test
    fun `not adding a task and getting the list results in an empty list`() {
        assert(dataSource.tasksList.isEmpty())
    }

    @Test
    fun `adding a task and getting the list results in a non empty list`() {
        dataSource.add(task)
        assert(!dataSource.tasksList.isEmpty())
    }

    @Test
    fun `adding a task and getting the list results in list of size one`() {
        dataSource.add(task)
        assert(dataSource.tasksList.size == 1)
    }

    @Test
    fun `add a task, replace it, and then get it`() {
        val other = Task("Other title", "other description", Date())
        dataSource.add(task)
        dataSource.replace(other, 0)
        assert(other == dataSource.get(0))
    }
}
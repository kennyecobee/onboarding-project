package com.example.kennyr.onboardingproject.tasks.tasklist

import com.example.kennyr.onboardingproject.R
import com.example.kennyr.onboardingproject.Toolbar
import com.example.kennyr.onboardingproject.tasks.Task
import com.example.kennyr.onboardingproject.tasks.TaskDataSource
import com.example.kennyr.onboardingproject.util.StringProvider
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*

class TaskListPresenterTest {

    @Rule @JvmField val mockitoRule = MockitoJUnit.rule()!!

    @Mock private lateinit var view : TaskListView
    @Mock private lateinit var stringProvider : StringProvider
    @Mock private lateinit var dataSource : TaskDataSource
    @Mock private lateinit var toolbar : Toolbar

    @InjectMocks private lateinit var presenter : TaskListPresenter

    @Before
    fun setup() {
        stubNewTaskClicks(Observable.empty())
        stubEditTaskClicks(Observable.empty())
    }

    @Test
    fun `on start, toolbar will be set up`() {
        presenter.start()
        verify(toolbar).showTitle(stringProvider.getString(R.string.task_list_title))
    }

    @Test
    fun `on start, a null task list hides the list`() {
        presenter.start()
        verify(view).hideTasksList()
    }

    @Test
    fun `on start, a null task list shows no task message`() {
        presenter.start()
        verify(view).showNoTasksMessage()
    }

    @Test
    fun `on start, an empty task list hides the list`() {
        stubGetEmptyTasksList()
        presenter.start()
        verify(view).hideTasksList()
    }

    @Test
    fun `on start, an empty task list shows no task message`() {
        stubGetEmptyTasksList()
        presenter.start()
        verify(view).showNoTasksMessage()
    }

    @Test
    fun `on start, a non empty task list hides the no tasks message`() {
        stubGetTasksList()
        presenter.start()
        verify(view).hideNoTasksMessage()
    }

    @Test
    fun `on start, a non empty task list shows the tasks list`() {
        stubGetTasksList()
        presenter.start()
        verify(view).showTasksList(argWhere { it.size == 1 })
    }

    @Test
    fun `on new task click, task detail is shown`() {
        stubNewTaskClicks(Observable.just(true))
        presenter.start()
        verify(view).showTaskDetail()
    }

    @Test
    fun `on edit task click, task detail is shown`() {
        stubEditTaskClicks(Observable.just(0))
        presenter.start()
        verify(view).showTaskDetail(0)
    }

    private fun stubNewTaskClicks(clicks : Observable<Boolean>) = view.stub { on { listenToNewTaskClicks() } doReturn clicks }
    private fun stubEditTaskClicks(clicks : Observable<Int>) = view.stub { on { listenToEditTaskClicks() } doReturn clicks }

    private fun stubGetEmptyTasksList() = dataSource.stub { on { tasksList } doReturn emptyList<Task>() }
    private fun stubGetTasksList() = dataSource.stub { on { tasksList } doReturn listOf(Task("title", "description", Date())) }
}
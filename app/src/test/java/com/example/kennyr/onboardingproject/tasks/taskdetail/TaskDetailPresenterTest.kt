package com.example.kennyr.onboardingproject.tasks.taskdetail

import android.support.v4.util.Pair
import com.example.kennyr.onboardingproject.R
import com.example.kennyr.onboardingproject.Toolbar
import com.example.kennyr.onboardingproject.tasks.Task
import com.example.kennyr.onboardingproject.tasks.TaskDataSource
import com.example.kennyr.onboardingproject.util.DateConverter
import com.example.kennyr.onboardingproject.util.StringProvider
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.stub
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*

class TaskDetailPresenterTest {

    @Rule @JvmField val mockitoRule = MockitoJUnit.rule()!!

    @Mock private lateinit var view : TaskDetailView
    @Mock private lateinit var stringProvider : StringProvider
    @Mock private lateinit var dataSource : TaskDataSource
    @Mock private lateinit var toolbar : Toolbar

    @InjectMocks private lateinit var presenter : TaskDetailPresenter

    private val date = Date()
    private val task = Task("title", "destcription", getDate())

    @Before
    fun setup() {
        stubDateClicks(Observable.empty())
        stubSaveClicks(Observable.empty())
        stubGetSelectedDate()
    }

    @Test
    fun `on start, tile is set`() {
        presenter.start()
        verify(toolbar).showTitle(stringProvider.getString(R.string.task_detail_title))
    }

    @Test
    fun `on start, current date will be displayed`() {
        presenter.start()
        verify(view).showDate(DateConverter.dateToMonthDayYearLong(getDate()))
    }

    @Test
    fun `on start, listen for change date clicks`() {
        presenter.start()
        verify(view).listenToChangeDateClicks()
    }

    @Test
    fun `on start, listen for save task clicks`() {
        presenter.start()
        verify(view).listenToSaveTaskClicks()
    }

    @Test
    fun `when edit task requested, source retrieves correct task`() {
        stubNonEmptySource()
        presenter.onEditExisitingTaskRequested(any())
        verify(dataSource).get(any())
    }

    @Test
    fun `when edit task requested, view shows correct task details`() {
        stubNonEmptySource()
        presenter.onEditExisitingTaskRequested(any())
        verify(view).showExistingTaskDetails(task.title, task.description, DateConverter.dateToMonthDayYearLong(task.date))
    }

    @Test
    fun `when select date requested, view provides the selected date`() {
        stubDateClicks(Observable.just(true))
        presenter.start()
        verify(view).selectedDate
    }

    @Test
    fun `when date selected, view shows updated date`() {
        presenter.start()
        verify(view).showDate(DateConverter.dateToMonthDayYearLong(getDate()))
    }

    @Test
    fun `when saving task, show error message if title is empty`() {
        view.stub { on { listenToSaveTaskClicks() } doReturn Observable.just(Pair.create(Task(null, "Description", getDate()), -1)) }
        presenter.start()
        verify(view).showEmptyFieldErrorMessage(stringProvider.getString(R.string.missing_title_entry))
    }

    @Test
    fun `when saving task, show error message if description is empty`() {
        view.stub { on { listenToSaveTaskClicks() } doReturn Observable.just(Pair.create(Task("title", null, getDate()), -1)) }
        presenter.start()
        verify(view).showEmptyFieldErrorMessage(stringProvider.getString(R.string.missing_description_entry))
    }

    @Test
    fun `when saving task, add task to dataSource if index is less than zero`() {
        stubNegIndexPair()
        presenter.start()
        verify(dataSource).add(task)
    }

    @Test
    fun `when saving task, show task list even if index is less than zero`() {
        stubNegIndexPair()
        presenter.start()
        verify(view).showTasksList()
    }

    @Test
    fun `when saving task, add task to dataSource if index is larger than amount of tasks`() {
        stubNonEmptySource()
        stubNonEmptySourceList()
        stubLargeIndexPair()
        presenter.start()
        verify(dataSource).add(task)
    }

    @Test
    fun `when saving task, show task list even if index is larger than amount of tasks`() {
        stubNonEmptySource()
        stubNonEmptySourceList()
        stubLargeIndexPair()
        presenter.start()
        verify(view).showTasksList()
    }

    @Test
    fun `when saving task with valid index, replace the existing task in the dataSource`() {
        stubNonEmptySource()
        stubNonEmptySourceList()
        stubValidIndexPair()
        presenter.start()
        verify(dataSource).replace(task, 0)
    }

    @Test
    fun `when saving task with valid index, show the task list`() {
        stubNonEmptySource()
        stubNonEmptySourceList()
        stubValidIndexPair()
        presenter.start()
        verify(view).showTasksList()
    }

    private fun stubNegIndexPair() {
        view.stub { on { listenToSaveTaskClicks() } doReturn Observable.just(Pair.create(task, -1)) }
    }

    private fun stubLargeIndexPair() {
        view.stub { on { listenToSaveTaskClicks() } doReturn Observable.just(Pair.create(task, 10)) }
    }

    private fun stubValidIndexPair() {
        view.stub { on { listenToSaveTaskClicks() } doReturn Observable.just(Pair.create(task, 0)) }
    }

    private fun stubDateClicks(clicks : Observable<Boolean>) = view.stub { on { listenToChangeDateClicks() } doReturn clicks }
    private fun stubSaveClicks(clicks : Observable<Pair<Task, Int>>) = view.stub { on { listenToSaveTaskClicks() } doReturn clicks }

    private fun stubGetSelectedDate() = view.stub { on { selectedDate } doReturn Observable.just(getDate()) }
    private fun stubNonEmptySource() = dataSource.stub { on { get(any()) } doReturn task }
    private fun stubNonEmptySourceList() = dataSource.stub { on { tasksList } doReturn listOf(task) }

    private fun getDate() : Date {
        return date
    }
}
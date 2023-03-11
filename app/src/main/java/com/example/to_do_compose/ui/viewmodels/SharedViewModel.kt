package com.example.to_do_compose.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_compose.domain.models.ToDoTask
import com.example.to_do_compose.domain.use_cases.UseCases
import com.example.to_do_compose.utils.Action
import com.example.to_do_compose.utils.Constants.MAX_DESCRIPTION_LENGTH
import com.example.to_do_compose.utils.Constants.MAX_TITLE_LENGTH
import com.example.to_do_compose.utils.Priority
import com.example.to_do_compose.utils.SearchTopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.to_do_compose.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val useCases: UseCases): ViewModel() {

    private val _searchTopBarState : MutableState<SearchTopBarState> = mutableStateOf(SearchTopBarState.CLOSED)
    val searchTopBarState by _searchTopBarState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState by _searchTextState

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())

    val allTasks: StateFlow<List<ToDoTask>> = _allTasks

    private val _selectedTask: MutableState<ToDoTask?> = mutableStateOf(null)
    val selectedTask: State<ToDoTask?> = _selectedTask

    private val _taskError: MutableState<Boolean> = mutableStateOf(false)

    private val _action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)
    val action: State<Action> = _action

    private val _deletedItem: MutableState<Int> = mutableStateOf(-1)
    val deletedItem: State<Int> = _deletedItem

    private val _lastActonResult: MutableState<Result> = mutableStateOf(Result.SUCCESS)
    val lastActionResult: State<Result> = _lastActonResult

    private val _isFirstShow: MutableState<Boolean> = mutableStateOf(true)
    val isFirstShow by _isFirstShow

    private val _sortState: MutableStateFlow<Priority> = MutableStateFlow(Priority.None)
    val sortState: StateFlow<Priority> = _sortState

    init {
        readSortState()
        viewModelScope.launch(Dispatchers.Unconfined) {
            delay(500)
            _isFirstShow.value = false
        }
    }

    fun emptySelectedTask(){
        _selectedTask.value = null
    }

    private fun updateDeletedItem(item: Int) {
        _deletedItem.value = item
    }

    fun updateSearchText(newText: String){
        _searchTextState.value = newText
        searchTasks()
    }

    fun updateTopBarState(state: SearchTopBarState){
        _searchTopBarState.value = state
    }

    fun getAllTasks(sortState: Priority){
        viewModelScope.launch(Dispatchers.IO) {
            when(sortState){
                Priority.None -> {
                    useCases.getAllTaskUseCase().collect {
                        _allTasks.value = it
                    }
                }
                Priority.Low -> {
                    useCases.sortByLowPriorityUseCase().collect {
                        _allTasks.value = it
                    }
                }
                Priority.High -> {
                    useCases.sortByHighPriorityUseCase().collect {
                        _allTasks.value = it
                    }
                }
                else -> { }
            }
        }
    }

    fun getSelectedTask(taskId: Int){
        if (taskId != -1){
            viewModelScope.launch(Dispatchers.IO){
                useCases.getSelectedTaskUseCase(taskId = taskId).collect { task ->
                    if (task != null)
                        _selectedTask.value = task
                }
            }
        }else{
            _selectedTask.value = ToDoTask(-1, "", "", Priority.None)
        }
    }

    fun updateTaskFields(toDoTask: ToDoTask?){
        if (toDoTask != null){
            if (
                (toDoTask.description.length <= MAX_DESCRIPTION_LENGTH) &&
                (toDoTask.title.length <= MAX_TITLE_LENGTH)
            )
                _selectedTask.value = toDoTask
        }
    }

    fun validateExeAction(action: Action): Boolean{
        return if (action == Action.NO_ACTION || action == Action.DELETE) {
            _taskError.value = false
            true
        } else {
            return if (selectedTask.value?.title?.isNotEmpty() == true &&
                selectedTask.value?.description?.isNotEmpty() == true)
            {
                _taskError.value = false
                true
            } else {
                _taskError.value = true
                false
            }
        }
    }

    fun titleErrorManager(titleError: String): String? {
        return if (_taskError.value){
            if (selectedTask.value?.title?.isNotEmpty() == true) null
            else titleError
        } else null
    }

    fun descriptionErrorManager(descriptionError: String): String? {
        return if (_taskError.value){
            if (selectedTask.value?.description?.isNotEmpty() == true) null
            else descriptionError
        } else null
    }

    fun updateAction(action: Action) {
        if (action != this.action.value){
            _action.value = action
        }

    }

    fun handleDatabaseActions() {
        _taskError.value = false
        when(action.value){
            Action.ADD -> {
                addTask()
                if (searchTopBarState == SearchTopBarState.OPENED){
                    updateSearchText("")
                    _searchTopBarState.value = SearchTopBarState.CLOSED
                    getAllTasks(sortState.value)
                }
            }
            Action.UPDATE -> {
                updateTask()
            }
            Action.DELETE -> {
                if (deletedItem.value != -1){
                    deleteTask()
                } else {
                    updateDeletedItem(selectedTask.value?.id?: -1)
                    return
                }
            }
            Action.DELETE_ALL -> {
                deleteAllTasks()
            }
            Action.UNDO -> {
                updateDeletedItem(-1)
                return
            }
            else -> { }
        }
        updateAction(Action.NO_ACTION)
    }

    private fun actionsErrorManager(result: Result){
        return if (result == Result.SUCCESS){
            _lastActonResult.value = Result.SUCCESS
        } else {
            _lastActonResult.value = Result.ERROR
        }
    }

    private fun addTask(){
        viewModelScope.launch(Dispatchers.IO) {
            selectedTask.value?.let { task ->
                if (useCases.addTaskUseCase(
                        ToDoTask(
                            title = task.title,
                            description = task.description,
                            priority = task.priority)
                    ) > 0){
                    actionsErrorManager(Result.SUCCESS)
                } else {
                    actionsErrorManager(Result.ERROR)
                }
                _searchTopBarState.value = SearchTopBarState.CLOSED
            }
        }
    }

    private fun updateTask(){
        viewModelScope.launch(Dispatchers.IO){
            selectedTask.value?.let {
                if (useCases.updateTaskUseCase(it) > 0){
                    actionsErrorManager(Result.SUCCESS)
                } else
                    actionsErrorManager(Result.ERROR)
            }
        }
    }

    private fun deleteTask(){
        viewModelScope.launch(Dispatchers.IO){
            selectedTask.value?.let { task ->
                useCases.deleteTaskUseCase(task)?.let {
                    if (it > 0){
                        actionsErrorManager(Result.SUCCESS)
                        delay(500)
                        updateDeletedItem(-1)
                        updateTaskFields(null)
                    } else actionsErrorManager(Result.ERROR)
                }?: actionsErrorManager(Result.ERROR)
            }?: actionsErrorManager(Result.ERROR)
        }
    }

    fun searchTasks(){
        viewModelScope.launch(Dispatchers.IO){
            if (searchTextState.isNotEmpty()){
                useCases.searchTaskUseCase("%$searchTextState%").collect {
                    _allTasks.value = it
                }
            } else {
                getAllTasks(sortState.value)
            }
        }
    }

    private fun deleteAllTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteAllTaskUseCase()?.let {
                if (it > 0){
                    actionsErrorManager(Result.SUCCESS)
                } else {
                    actionsErrorManager(Result.ERROR)
                }
            }
        }
    }

    private fun readSortState() = viewModelScope.launch(Dispatchers.IO) {
        useCases.readSortState().map { Priority.valueOf(it) }.collect{ _sortState.value = it }
    }

    fun persistSortState(priority: Priority) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.persistSortState(priority)
        }
    }
}
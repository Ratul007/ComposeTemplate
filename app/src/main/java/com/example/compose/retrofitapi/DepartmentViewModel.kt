package com.example.compose.retrofitapi

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DepartmentViewModel : ViewModel() {
    private val _departmentsState = mutableStateOf<List<DepartmentModel>>(emptyList())
    val departmentsState: State<List<DepartmentModel>> = _departmentsState

    init {
        fetchDepartments()
    }

    private fun fetchDepartments() {
        viewModelScope.launch {
            try {
                val response = DepartmentInstance().departmentApiService.getDepartments()
                _departmentsState.value = response.toList()
                Log.e("DepartmentViewModel", "Success")
            } catch (e: Exception) {
                Log.e("DepartmentViewModel", "Error")
            }
        }
    }
}

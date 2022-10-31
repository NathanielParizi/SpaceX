package com.example.spacex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.data.LaunchResponseItem
import com.example.spacex.network.LaunchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(private val launchRepo: LaunchRepositoryImpl) :
    ViewModel() {

    private val _launch = MutableLiveData<List<LaunchResponseItem>>()
    val launch: LiveData<List<LaunchResponseItem>>
        get() = _launch

    var launchSelected: LaunchResponseItem? = null

    init {
        fetchLaunches()
    }

    private fun fetchLaunches() {
        viewModelScope.launch {
            launchRepo.fetchLaunches().let {
                _launch.postValue(it)
            }
        }
    }
}
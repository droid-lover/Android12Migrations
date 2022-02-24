package com.sachin.android12migrations.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//just to make some apis or fetch some data
class SplashViewModel : ViewModel() {


  private val _isDataLoaded = MutableLiveData<Boolean>(false)
  val isDataLoaded: LiveData<Boolean> = _isDataLoaded

  init {
    viewModelScope.launch {

      delay(5000)
      _isDataLoaded.value = true
    }
  }
}
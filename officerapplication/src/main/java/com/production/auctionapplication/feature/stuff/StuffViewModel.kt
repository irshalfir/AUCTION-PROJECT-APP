package com.production.auctionapplication.feature.stuff

import android.app.Application
import androidx.lifecycle.*
import com.production.auctionapplication.R
import com.production.auctionapplication.repository.networking.AuctionApi
import com.production.auctionapplication.repository.networking.models.stuff.StuffResponse
import kotlinx.coroutines.*
import timber.log.Timber

class StuffViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    /**
     * Coroutine scope for a new Job using Main Dispatcher, because
     * this is affected to the UI.
     */
    private val coroutineScope = CoroutineScope(viewModelJob+Dispatchers.Main)

    // Encapsulation the variable
    private val _stuff = MutableLiveData<List<StuffResponse>>()
    val stuff: LiveData<List<StuffResponse>>
        get() = _stuff

    /**
     * Used for trigger some event from this properties value
     */
    private val _clickHandler = MutableLiveData<Boolean>()
    val clickHandler: LiveData<Boolean>
        get() = _clickHandler

    val displayStuffPrice = Transformations.map(_stuff) { stuff ->
        stuff.forEach {
            application.applicationContext.getString(
                R.string.display_price, it.startedPrice
            )
        }
    }.toString()

    init {
        getAllStuffData()
    }

    /**
     * Getting all stuff data from the API
     */
    private fun getAllStuffData() {
        // launching the coroutine
        coroutineScope.launch {
            // switch to the I/O thread
            withContext(Dispatchers.IO) {
                val getStuffDeferred =
                    AuctionApi.retrofitService.getAllStuffAsync()
                try {
                    val result  = getStuffDeferred.await()
                    _stuff.postValue(result.stuffData)
                    Timber.i(_stuff.toString())
                } catch (e: Exception) {
                    Timber.e(e.message.toString())
                }
            }
        }
    }

    fun onButtonClick() {
        _clickHandler.value = true
    }

    fun restartClickState() {
        if (_clickHandler.value == true) {
            _clickHandler.value = false
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
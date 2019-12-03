package com.travelcar_test.ui.car_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.travelcar_test.data.CarsRepository
import com.travelcar_test.data.model.Cars
import com.travelcar_test.data.network.CarApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarsListViewModel : ViewModel() {

    val carsRepository: CarsRepository = CarsRepository(CarApiService.makeApiService())
    val carLiveData = MutableLiveData<List<Cars>>()
    var queryLiveData = MutableLiveData<List<Cars>>()
    val list = mutableListOf<Cars>()

    fun getCars() {

        CoroutineScope(Dispatchers.IO).launch {
            val result = carsRepository.getCars()
            try {
                withContext(Dispatchers.Main) {
                    carLiveData.value = result
                }
            } catch (e: Exception) {
                Log.e("Error ", "${e.cause}")
            }
        }

    }

    fun getCarsData(): LiveData<List<Cars>> {
        return carLiveData!!
    }

    fun getupdateCarList(query: String) {

        list.clear()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    carLiveData.value?.forEach { cars ->

                        if (cars.make.toLowerCase().contains(query.toLowerCase())) {
                            val car : Cars = cars
                            list.add(car)

                        }else if (query.length<0){
                            list.add(cars)
                        }
                    }

                    queryLiveData.value = list

                }
            } catch (e: Exception) {
                Log.e("Error ", "${e.cause}")
            }
        }
    }

    fun getQueryLiveData(): LiveData<List<Cars>> {
        return queryLiveData
    }

}
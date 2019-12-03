package com.travelcar_test.data

import com.travelcar_test.data.model.Cars
import com.travelcar_test.data.network.CarsApi

class CarsRepository (val carsApi: CarsApi) : BaseRepository() {

   suspend fun getCars(): MutableList<Cars>? {
        val carsResponse = safeApiCall(
            call = { carsApi.getCars().await() },
            errorMessage = "Oops .. Something went wrong"
        )
        return carsResponse?.toMutableList()
    }
}
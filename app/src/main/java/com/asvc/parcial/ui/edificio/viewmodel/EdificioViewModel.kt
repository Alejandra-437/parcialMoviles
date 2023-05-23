package com.asvc.parcial.ui.edificio.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EdificioViewModel() : ViewModel() {
    var name = MutableLiveData("")
    var category = MutableLiveData("")
    var description = MutableLiveData("")
    var qualification = MutableLiveData("")
    var status = MutableLiveData("")
    fun getBuildings() = repository.getBuildings()

    fun addBuilding(building: BuildingModel) = repository.addBuiilding(building)
    fun createMovie(){
        if(!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }
        val building = BuildingModel(
            name.value!!,
            category.value!!,
            description.value!!,
            qualification.value!!
        )
        addBuilding(building)
        clearData()

        status.value =  BUILDING_CREATED
    }

    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            category.value.isNullOrEmpty() -> return false
            description.value.isNullOrEmpty() -> return false
            name.value.isNullOrEmpty() -> return false
            qualification.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearData(){
        name.value = ""
        category.value = ""
        description.value = ""
        qualification.value = ""
    }

    fun clearStatus(){
        status.value = INACTIVE
    }

    fun setSelectedMovie(building : BuildingModel){
        name.value = building.name
        category.value = building.location
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MovieReviewerApplication
                MovieViewModel(app.movieRepository)
            }
        }
        const val BUILDING_CREATED = "Building created"
        const val WRONG_INFORMATION = "Wrong information"
        const val  INACTIVE = ""
    }

}
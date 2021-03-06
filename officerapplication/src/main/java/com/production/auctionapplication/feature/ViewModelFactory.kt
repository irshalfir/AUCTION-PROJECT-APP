package com.production.auctionapplication.feature

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.production.auctionapplication.feature.administrator.officer.OfficerViewModel
import com.production.auctionapplication.feature.administrator.officer.createupdate.CreateUpdateOfficerViewModel
import com.production.auctionapplication.feature.signin.SigninViewModel
import com.production.auctionapplication.feature.splashscreen.SplashViewModel
import com.production.auctionapplication.feature.stuff.StuffViewModel
import com.production.auctionapplication.feature.stuff.createupdate.CreateUpdateStuffViewModel
import com.production.auctionapplication.feature.stuffcategory.createupdate.CreateUpdateStuffCategoryViewModel

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                SigninViewModel(application) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(application) as T
            }
            modelClass.isAssignableFrom(CreateUpdateStuffCategoryViewModel::class.java) -> {
                CreateUpdateStuffCategoryViewModel(application) as T
            }
            modelClass.isAssignableFrom(CreateUpdateStuffViewModel::class.java) -> {
                CreateUpdateStuffViewModel(application) as T
            }
            modelClass.isAssignableFrom(CreateUpdateOfficerViewModel::class.java) -> {
                CreateUpdateOfficerViewModel(application) as T
            }
            modelClass.isAssignableFrom(StuffViewModel::class.java) -> {
                StuffViewModel(application) as T
            }
            modelClass.isAssignableFrom(OfficerViewModel::class.java) -> {
                OfficerViewModel(application) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
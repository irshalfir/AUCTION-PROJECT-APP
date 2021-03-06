package com.production.auctionapplication.repository

import com.production.auctionapplication.repository.database.OfficerAccountData
import com.production.auctionapplication.repository.networking.models.auth.OfficerAuth
import com.production.auctionapplication.repository.database.OfficerDatabase
import com.production.auctionapplication.repository.networking.models.auth.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class OfficerRepository (private val database: OfficerDatabase) {

    /**
     * Save data to database.
     */
    suspend fun saveAccountData(officerData: OfficerAuth) {
        withContext(Dispatchers.IO) {
            database.officerDatabaseDao
                .insertOfficerData(officerData.asDatabaseModel())
            Timber.i("Success insert data")
        }
    }

    /**
     * Get data from database.
     */
    suspend fun getAccountData(): OfficerAccountData? {
        return withContext(Dispatchers.IO) {
            database.officerDatabaseDao
                .getOfficerData()
        }
    }

}
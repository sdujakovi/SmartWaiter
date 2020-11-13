package com.example.database

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(
    context: Context
) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "smartWaiter_data_store"
        )
    }

    val authToken: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH]
    }

    val userType: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USER_TYPE]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun saveUserType(userType: String) {
        dataStore.edit { preferences ->
            preferences[USER_TYPE] = userType
        }
    }

    suspend fun clear(){
        dataStore.edit {  preferences ->
             preferences.clear()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val USER_TYPE = preferencesKey<String>("type_user")
    }


}
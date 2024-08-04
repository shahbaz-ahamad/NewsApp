package com.shahbaz.news.presentation.onboardingrepo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.shahbaz.news.util.Constant
import com.shahbaz.news.util.Constant.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingRepo(
    private val context: Context
) {

    suspend fun saveAppEntry() {
        context.dataStore.edit { setting ->
            setting[PreferencesKey.APP_ENTRY] = true
        }
    }

    fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { prefrences ->
            prefrences[PreferencesKey.APP_ENTRY] ?: false
        }
    }


    private val readOnlyProperty = preferencesDataStore(name = USER_SETTING)
    private val Context.dataStore: DataStore<Preferences> by readOnlyProperty

    private object PreferencesKey {
        val APP_ENTRY = booleanPreferencesKey(Constant.APP_ENTRY)
    }
}
package com.example.to_do_compose.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.to_do_compose.domain.repositories.DataStoreRepository
import com.example.to_do_compose.utils.Constants
import com.example.to_do_compose.utils.Priority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCE_NAME)

class DataStoreRepositoryImpl(context: Context):
    DataStoreRepository {
    private object PreferencesKeys{
        val sortStateKey = stringPreferencesKey(name = Constants.PREFERENCE_SORT_KEY)
    }
    private val dataStore = context.dataStore

    override suspend fun persistSortState(priority: Priority) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.sortStateKey] = priority.name
        }
    }

    override val readSortState: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException)
                emit(emptyPreferences())
            else
                throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.sortStateKey]?: Priority.None.name
        }
}
package org.cornmuffin.roomwordssample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository =
        WordRepository(WordRoomDatabase.getDatabase(application, viewModelScope).wordDao())

    val allWords: LiveData<List<Word>> = repository.allWords

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

package app.kitabcha.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kitabcha.data.entity.MangaEntity
import app.kitabcha.data.repository.MangaRepository
import app.kitabcha.source.AvailableSources
import app.kitabcha.source.model.SManga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class BrowseScreenViewModel @Inject constructor(
    //TODO :  Add Manga repository in which we are going to inject
    private val repository: MangaRepository
): ViewModel() {

    
    val lazyListFlag = MutableStateFlow(false)
    private val _searched_manga = MutableStateFlow("")
    val manga_searched = _searched_manga.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val searchingInProgress = _isSearching.asStateFlow()
    val _source = AvailableSources.sources.values.first()

    // manga list will be the list we will receive from the server with respect to our query
    val mangaList_toDisplay = MutableStateFlow(listOf<MangaEntity>()) // TODO: Add data type of this list

    fun getMangas(source: Long)
    {
        viewModelScope.launch {
            _source.getListing(1)
                .map { // TODO: pass page number properly
                MangaEntity(
                    mangaURL = it.url,
                    mangaTag = "",
                    mangaDesc = it.description,
                    mangaTitle = it.title,
                    mangaAuthor = it.author,
                    sourceID = 1L // TODO:
                )
            }.also { repository.insert(*it.toTypedArray()) } .also { mangaList_toDisplay.tryEmit(it) }.also{lazyListFlag.tryEmit(true)}
        }
    }



//    fun loginUser(userName: String, password: String, callback: (UserEntity?) -> Unit) {
//        viewModelScope.launch {
//            callback(repository.getUser(userName, password))
//        }
//    }


    // this will detect when something changes in the ui of our search bar
    fun onSearchTextChange(text: String)
    {
        _searched_manga.value = text
    }

}




// mangasFoundBySearch.
//    val mangasFoundBySearch = manga_searched.combine(mangaList)
//    { text, mangaFoundBySearch ->
//        // the code in this block will run when either the "manga_searched" variable changes or "mangaList" changes
//        // and the final result will be in the "mangaFoundBySearch" variable
//        if(text.isBlank()) // if list is empty jut display empty
//        {
//            null
//            // mangasFoundBySearch
//        }else
//        {
//            // TODO:
//            //mangasFoundBySearch.filter{
//                // here we will add our query with 'it.ourquery' ourquery will be of our class
//            //}
//        }
//    }.stateIn(
//        viewModelScope,
//        // the block above will be executed for 5 seconds if the ui of search bar stops updating
//        SharingStarted.WhileSubscribed(5000),
//        mangaList.value // show default value of manga List we
//    )

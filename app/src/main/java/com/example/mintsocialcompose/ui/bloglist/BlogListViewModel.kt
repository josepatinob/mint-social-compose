package com.example.mintsocialcompose.ui.bloglist

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.R
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.repository.AmplifyRepository
import com.example.mintsocialcompose.repository.BlogRepository
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel @Inject constructor(
    private val blogRepository: BlogRepository,
    private val amplifyRepository: AmplifyRepository
) : ViewModel() {
    companion object {
        private const val TAG = "BLOG_LIST_VIEW_MODEL"
    }

    private var _blogList = MutableLiveData<List<Blog>>()
    val blogList: LiveData<List<Blog>> get() = _blogList

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private var _isSignedIn = MutableLiveData<Boolean>()
    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    private var _filter = MutableLiveData(BlogFilter.All)
    val filter: LiveData<BlogFilter> get() = _filter

    fun onFilterChange(filter: BlogFilter) {
        _filter.value = filter
        fetchBlogs(filter)
    }

    init {
        checkSignInStatus()
        _filter.value?.let { fetchBlogs(it) }
    }

    private fun fetchBlogs(filter: BlogFilter) {
        val authUser = amplifyRepository.getCurrentUser()
        when (filter) {
            BlogFilter.All -> {
                getBlogs(null, BlogFilter.All.filter)
            }
            BlogFilter.Mine -> {
                if (authUser != null) {
                    getBlogs(authUser.userId, BlogFilter.Mine.filter)
                } else {
                    /* TODO: Add a snackbar message */
                }
            }
            BlogFilter.Others -> {
                if (authUser != null) {
                    getBlogs(authUser.userId, BlogFilter.Others.filter)
                } else {
                    /* TODO: Add a snackbar message */
                }
            }
        }
    }

    private fun getBlogs(authorId: String?, authorFilter: String?) = viewModelScope.launch {
        try {
            _status.value = Status.Loading
            _blogList.value = blogRepository.getBlogs(authorId, authorFilter)
            _status.value = Status.Success
        } catch (e: Exception) {
            _status.value = Status.Error
            Log.d(TAG, e.message.toString())
        }
    }

    fun checkSignInStatus() = viewModelScope.launch {
        _isSignedIn.value = amplifyRepository.isSignedIn()
    }
}
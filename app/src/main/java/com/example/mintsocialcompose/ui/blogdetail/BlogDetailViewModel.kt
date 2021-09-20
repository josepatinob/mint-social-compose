package com.example.mintsocialcompose.ui.blogdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.repository.BlogRepository
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BlogDetailUiState(
    val blog: Blog? = null,
    val isLoading: Boolean,
    val errorMessages: List<String> = emptyList()
)

@HiltViewModel
class BlogDetailViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private var _uiState = MutableLiveData<BlogDetailUiState>()
    val uiState: LiveData<BlogDetailUiState> get() = _uiState

    fun getBlogById(blogId: String) = viewModelScope.launch {
        try {
            _uiState.value =
                BlogDetailUiState(blog = blogRepository.getBlogById(blogId), isLoading = false)
        } catch (e: Exception) {
            _uiState.value =
                BlogDetailUiState(isLoading = false, errorMessages = listOf("Network error"))
            Log.d(TAG, e.message.toString())
        }
    }

    companion object {
        private const val TAG = "BLOG_DETAIL_VIEW_MODEL"
    }
}
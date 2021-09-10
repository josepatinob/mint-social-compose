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

@HiltViewModel
class BlogDetailViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {
    private var _blog = MutableLiveData<Blog>()
    val blog: LiveData<Blog> get() = _blog

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    fun getBlogById(blogId: String) = viewModelScope.launch {
        try {
            _blog.value = blogRepository.getBlogById(blogId)
            _status.value = Status.Success
        } catch (e: Exception) {
            _status.value = Status.Error
            Log.d(TAG, e.message.toString())
        }
    }

    companion object {
        private const val TAG = "BLOG_DETAIL_VIEW_MODEL"
    }
}
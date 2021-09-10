package com.example.mintsocialcompose.ui.createblog

import android.util.Log
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.model.PostBlogRequest
import com.example.mintsocialcompose.repository.AmplifyRepository
import com.example.mintsocialcompose.repository.BlogRepository
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateBlogViewModel @Inject constructor(
    private val blogRepo: BlogRepository,
    private val amplifyRepository: AmplifyRepository
) : ViewModel() {

    companion object {
        const val TAG = "CREATE_BLOG_VIEW_MODEL"
    }

    val maxCharCount = 4000

    private var _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    private var _imageUrlError = MutableLiveData<Boolean>()
    val imageUrlError: LiveData<Boolean> get() = _imageUrlError

    private var _blogTitle = MutableLiveData<String>()
    val blogTitle: LiveData<String> get() = _blogTitle

    private var _blogTitleError = MutableLiveData<Boolean>()
    val blogTitleError: LiveData<Boolean> get() = _blogTitleError

    private var _content = MutableLiveData<String>()
    val content: LiveData<String> get() = _content

    private var _contentError = MutableLiveData<Boolean>()
    val contentError: LiveData<Boolean> get() = _contentError

    private var _charCount = MutableLiveData<Int>()
    val charCount: LiveData<Int> get() = _charCount

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    // fetching blog
    private var _blog = MutableLiveData<Blog>()
    val blog: LiveData<Blog> get() = _blog

    private var _fetchBlogStatus = MutableLiveData<Status>()
    val fetchBlogStatus: LiveData<Status> get() = _fetchBlogStatus

    private var _updateBlogStatus = MutableLiveData<Status>()
    val updateBlogStatus: LiveData<Status> get() = _updateBlogStatus

    fun getBlogById(blogId: String) = viewModelScope.launch {
        try {
            _blog.value = blogRepo.getBlogById(blogId)
            _fetchBlogStatus.value = Status.Success
        } catch (e: Exception) {
            _fetchBlogStatus.value = Status.Error
            Log.d(TAG, e.message.toString())
        }
    }

//    fun checkUserSignedIn(
//        activity: FragmentActivity,
//        resources: Resources
//    ) = viewModelScope.launch {
//        if (!ampRepo.isSignedIn()) {
//            AlertDialog.Builder(activity).showNavAlert(resources, AlertTypes.CREATE_BLOG_AS_GUEST)
//                .apply {
//                    dialogBuilder.setPositiveButton(positiveDesc) { _, _ ->
//                        _navigation.value = positiveDest
//                    }
//                    dialogBuilder.setNegativeButton(negativeDesc) { dialog, _ ->
//                        _navigation.value = negativeDest
//                        dialog.cancel()
//                    }
//                    dialogBuilder.startDialog()
//                }
//        }
//    }

    fun postBlog() = viewModelScope.launch {
        try {
            _status.value = Status.Loading
            blogRepo.postBlog(
                PostBlogRequest(
                    blogTitle.value.toString(),
                    content.value.toString(),
                    imageUrl.value.toString(),
                    amplifyRepository.getCurrentUser()?.userId.toString(),
                    getUsername(amplifyRepository),
                    false
                )
            )
            _status.value = Status.Success
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }

//    private fun updateBlog() = viewModelScope.launch {
//        try {
//            _updateBlogStatus.value = Status.Loading
//            blogRepo.updateBlogById(
//                blog.value?.blogId.toString(),
//                PutBlogRequest(
//                    blog.value?.blogId.toString(),
//                    blogTitle.value.toString(),
//                    blogText.value.toString(),
//                    photoUrl.value.toString(),
//                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                    blog.value?.authorId.toString(),
//                    blog.value?.authorEmail.toString(),
//                    true
//                )
//            )
//            _updateBlogStatus.value = Status.Success
//        } catch (e: Exception) {
//            _updateBlogStatus.value = Status.Error
//            Log.d(TAG, e.message.toString())
//        }
//    }

    private fun getUsername(ampRepo: AmplifyRepository) =
        ampRepo.getCurrentUser()?.username.toString()

    fun onUrlChange(url: String) {
        _imageUrl.value = url
        _imageUrlError.value = !URLUtil.isValidUrl(url)
    }


    fun onBlogTitleChange(title: String) {
        _blogTitle.value = title
        _blogTitleError.value =
            _blogTitle.value.toString().isEmpty() || _blogTitle.value.toString().isBlank()
    }

    fun onContentChange(text: String) {
        _content.value = text
        val charCount = text.count()
        if (charCount >= maxCharCount) {
            _contentError.value = true
            _charCount.value = maxCharCount
        } else {
            _charCount.value = charCount
            _contentError.value = false
        }
    }
}
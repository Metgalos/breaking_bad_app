package com.example.breakingbadapp.presentationlayer.screen.characters

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.repository.CharacterRepository
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter : MvpPresenter<CharactersFragmentView>() {

    @Inject
    lateinit var repository: CharacterRepository

    private var page = INITIAL_PAGE
    private var isLoading = false

    init {
        App.appComponent.inject(this)
        getCharacters()
    }

    fun getOnScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisible =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val adapter = recyclerView.adapter as CharactersAdapter

                if (lastVisible == adapter.getLastPosition() && !isLoading) {
                    getCharacters { recyclerView.removeOnScrollListener(this) }
                }
            }
        }

    private fun getCharacters(onLastPage: () -> Unit = {}) {
        isLoading = true
        repository.getCharacters(page, PAGE_SIZE)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                if (characters.size < PAGE_SIZE) onLastPage()
                viewState.hideProgressBar()
                viewState.addCharacters(characters)
                page ++
                isLoading = false
            }, { t: Throwable ->
                Timber.e(t)
                isLoading = false
            })
    }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val PAGE_SIZE = 5
    }
}
package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryViewHolderListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class RandomHistoryPresenter : MvpPresenter<RandomHistoryView>() {

    @Inject
    lateinit var repository: CharacterResponseRepository

    private var nextPage: Int = INITIAL_PAGE
    private var isLoading = false

    private val characters: MutableList<CharacterResponse> = mutableListOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent.inject(this)
        getHistoryItems(nextPage)
    }

    fun getOnScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisible =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                val adapter = recyclerView.adapter as RandomHistoryAdapter
                if (lastVisible != adapter.itemCount.minus(1) && !isLoading) return
                getHistoryItems(nextPage) { recyclerView.removeOnScrollListener(this) }
            }
        }

    fun getAdapterListener(): RandomHistoryViewHolderListener {
        return object : RandomHistoryViewHolderListener {
            override fun onDeleteItem(character: CharacterResponse) = remove(character)
        }
    }

    private fun remove(character: CharacterResponse) {
        repository.remove(character)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.i(" From ${characters.size.toString()}")
                characters.remove(character)
                Timber.i("To ${characters.size.toString()}")
                viewState.displayCharacters(characters.toList())
            }, { throwable: Throwable -> Timber.e(throwable) })
    }

    private fun getHistoryItems(page: Int, onLastPage: () -> Unit = {}): Disposable {
        isLoading = true
        return repository.getPaged(page, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                if (characters.size < PAGE_SIZE) {
                    onLastPage()
                } else {
                    ++nextPage
                }
                isLoading = false
                this.characters.addAll(characters)
                viewState.displayCharacters(this.characters.toList())
            }, { throwable: Throwable ->
                Timber.e(throwable)
                isLoading = false
            })
    }

    companion object {
        private const val PAGE_SIZE = 2
        private const val INITIAL_PAGE = 1
    }
}
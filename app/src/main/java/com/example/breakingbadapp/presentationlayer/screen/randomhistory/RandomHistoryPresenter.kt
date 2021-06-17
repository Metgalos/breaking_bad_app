package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.ConfirmationDialogOptions
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.presentationlayer.screen.character_detail.CharacterDetailFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryViewHolderListener
import com.github.terrakok.cicerone.Router
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

    @Inject
    lateinit var router: Router

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

            override fun onCharacterSelected(id: Int) {
                navigateToDetailScreen(id)
            }

            override fun onClearHistory() {
                val options = ConfirmationDialogOptions(R.string.clear_history_confirmation)
                viewState.displayConfirmation(options)
            }
        }
    }

    fun clearHistory() {
        viewState.hideConfirmation()
        repository.clear()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characters.clear()
                viewState.displayCharacters(characters)
            }, { throwable: Throwable? -> Timber.e(throwable) })
    }

    fun hideConfirmation() = viewState.hideConfirmation()

    private fun remove(character: CharacterResponse) {
        repository.remove(character)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characters.remove(character)
                viewState.displayCharacters(characters)
            }, { throwable: Throwable -> Timber.e(throwable) })
    }

    private fun getHistoryItems(page: Int, onLastPage: () -> Unit = {}): Disposable {
        isLoading = true
        return repository.getPaged(page, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                if (characters.size < PAGE_SIZE) onLastPage()
                nextPage++
                this.characters.addAll(characters)
                viewState.displayCharacters(this.characters)
                isLoading = false
            }, { throwable: Throwable ->
                Timber.e(throwable)
                isLoading = false
            })
    }

    private fun navigateToDetailScreen(id: Int) {
        router.navigateTo(CharacterDetailFragment.getScreen(id))
    }

    companion object {
        private const val PAGE_SIZE = 2
        private const val INITIAL_PAGE = 1
    }
}
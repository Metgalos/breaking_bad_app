package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class RandomHistoryPresenter : MvpPresenter<RandomHistoryView>() {

    @Inject
    lateinit var repository: CharacterResponseRepository

    private var currentPage: Int = INITIAL_PAGE

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent.inject(this)
        getHistoryItems(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                ++currentPage
                viewState.addItems(characters)
            }, { throwable: Throwable ->
                Timber.e(throwable)
            })
    }

    fun getOnScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisible =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                val adapter = recyclerView.adapter as RandomHistoryAdapter
                val lastPosition = adapter.getLastPosition()

                if (lastVisible != lastPosition) return

                getHistoryItems(currentPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ characters ->
                        if (characters.size < PAGE_SIZE) {
                            recyclerView.removeOnScrollListener(this)
                        } else {
                            ++currentPage
                        }
                        viewState.addItems(characters)
                    }, { throwable: Throwable ->
                        Timber.e(throwable)
                    })
            }
        }

    private fun getHistoryItems(page: Int): Single<List<CharacterResponse>> =
        repository.getPaged(page, PAGE_SIZE)

    companion object {
        private const val PAGE_SIZE = 2
        private const val INITIAL_PAGE = 1
    }
}
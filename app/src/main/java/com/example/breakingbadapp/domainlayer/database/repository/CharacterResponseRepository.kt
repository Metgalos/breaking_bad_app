package com.example.breakingbadapp.domainlayer.database.repository

import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import com.example.breakingbadapp.core.Observable as ChangeObservable

class CharacterResponseRepository @Inject constructor(
    private val dao: CharacterResponseDao
) {
    val historyChange = ChangeObservable<HistoryChangeObserver>()

    fun getPaged(page: Int, pageSize: Int): Observable<List<CharacterResponse>> {
        return dao.getPaged(page, pageSize)
    }

    fun getById(id: Int): Single<CharacterResponse> =
        dao.getById(id)
            .subscribeOn(Schedulers.io())

    fun insert(characterResponse: CharacterResponse): Completable =
        Completable.create { subscriber ->
            dao.insert(characterResponse)
            subscriber.onComplete()
        }

    fun remove(characterResponse: CharacterResponse): Completable =
        Completable.create { subscriber ->
            dao.remove(characterResponse)
            subscriber.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .doFinally {
                historyChange.notify { it.onDeleteItem(characterResponse) }
            }

    fun clear(): Completable =
        Completable.create { subscriber ->
            dao.clear()
            subscriber.onComplete()
        }
            .subscribeOn(Schedulers.io())
}
package com.example.breakingbadapp.domainlayer.database.repository

import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CharacterResponseRepository @Inject constructor(
    private val dao: CharacterResponseDao
) {

    fun getPaged(page: Int, pageSize: Int): Single<List<CharacterResponse>> {
        return Single.create { subscriber ->
            subscriber.onSuccess(dao.getPaged(page, pageSize))
        }
    }

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

    fun clear(): Completable =
        Completable.create { subscriber ->
            dao.clear()
            subscriber.onComplete()
        }
            .subscribeOn(Schedulers.io())
}
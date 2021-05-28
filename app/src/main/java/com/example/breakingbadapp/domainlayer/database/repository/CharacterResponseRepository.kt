package com.example.breakingbadapp.domainlayer.database.repository

import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CharacterResponseRepository @Inject constructor(
    private val dao: CharacterResponseDao
) {

//    val getAll: List<CharacterResponse> = dao.getAll()

    fun insert(
        characterResponse: CharacterResponse,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {

        return Completable.create {
            dao.insert(characterResponse)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onComplete, onError)

    }
}
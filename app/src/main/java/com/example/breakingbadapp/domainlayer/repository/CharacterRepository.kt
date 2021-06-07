package com.example.breakingbadapp.domainlayer.repository

import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: BreakingBadApi
) {

    fun getRandomCharacter(
        onComplete: (Response<List<SerialCharacter>>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {

        return Single.create<Response<List<SerialCharacter>>> { subscriber ->
            subscriber.onSuccess(api.getRandomCharacter().execute())
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onComplete, onError)
    }

    fun getCharacters(page: Int, pageSize: Int): Single<List<SerialCharacter>> {
        val offset = (page - 1) * pageSize
        return api.getCharacters(pageSize, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
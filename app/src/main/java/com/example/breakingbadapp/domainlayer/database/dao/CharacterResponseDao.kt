package com.example.breakingbadapp.domainlayer.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterResponseDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC, datetime DESC LIMIT :pageSize OFFSET (:page - 1) * :pageSize")
    fun getPaged(page: Int, pageSize: Int): Observable<List<CharacterResponse>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    fun getById(id: Int): Single<CharacterResponse>

    @Insert
    fun insert(character: CharacterResponse)

    @Delete
    fun remove(character: CharacterResponse)

    @Query("DELETE FROM $TABLE_NAME")
    fun clear()

    companion object {
        private const val TABLE_NAME = "character_responses"
    }
}
package com.example.hhtestus.downPanel.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hhtestus.downPanel.ApiSide.Vacancy

@Dao
interface vacDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vacancy: Vacancy)

    @Delete
    suspend fun delete(vacancy: Vacancy)

    @Query("SELECT * FROM vacancies WHERE isFavorite = 1")
    fun getAllFav(): LiveData<List<Vacancy>>

    @Query("SELECT * FROM vacancies WHERE id = :id")
    suspend fun getVacancyById(id: String): Vacancy?
}

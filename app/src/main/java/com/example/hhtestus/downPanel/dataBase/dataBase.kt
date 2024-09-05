package com.example.hhtestus.downPanel.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hhtestus.downPanel.ApiSide.Converter
import com.example.hhtestus.downPanel.ApiSide.Vacancy

@Database(entities = [Vacancy::class], version = 1)
@TypeConverters(Converter::class)
abstract class dataBase : RoomDatabase() {

    abstract fun vacdao(): vacDao

    companion object{
        @Volatile
        private var INSTANCE: dataBase? = null

        fun getDatabase(context: Context): dataBase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dataBase::class.java,
                    "vacancy_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
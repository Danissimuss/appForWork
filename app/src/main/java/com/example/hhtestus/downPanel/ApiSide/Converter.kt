package com.example.hhtestus.downPanel.ApiSide

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromAddress(address: Address?): String? {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(addressString: String?): Address? {
        return Gson().fromJson(addressString, Address::class.java)
    }

    // Experience
    @TypeConverter
    fun fromExperience(experience: Experience?): String? {
        return Gson().toJson(experience)
    }

    @TypeConverter
    fun toExperience(experienceString: String?): Experience? {
        return Gson().fromJson(experienceString, Experience::class.java)
    }

    // Salary
    @TypeConverter
    fun fromSalary(salary: Salary?): String? {
        return Gson().toJson(salary)
    }

    @TypeConverter
    fun toSalary(salaryString: String?): Salary? {
        return Gson().fromJson(salaryString, Salary::class.java)
    }

    // List<String> (для всех списков строк)
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(listString: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(listString, listType)
    }
}
package com.example.hhtestus.downPanel.ApiSide

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class ApiResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)
@Parcelize
data class Offer(
    val id: String,
    val title: String,
    val link: String,
    val button: Button? = null
) : Parcelable
@Parcelize
data class Button(
    val text: String
):Parcelable

@Entity(tableName = "vacancies")
@Parcelize
data class Vacancy(
   @PrimaryKey val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
): Parcelable

@Parcelize
data class Address(
    val town: String,
    val street: String,
    val house: String
):Parcelable

@Parcelize
data class Experience(
    val previewText: String,
    val text: String
):Parcelable

@Parcelize
data class Salary(
    val full: String,
    val short: String? = null
):Parcelable
package com.example.testprime.data.basket.model


import com.google.gson.annotations.SerializedName

data class OptionsDto(
    @SerializedName("carbohydrate")
    val carbohydrate: Double?,
    @SerializedName("carbohydrate_per_hundred_grams")
    val carbohydratePerHundredGrams: Double?,
    @SerializedName("energy")
    val energy: Double?,
    @SerializedName("energy_per_hundred_grams")
    val energyPerHundredGrams: Double?,
    @SerializedName("fat")
    val fat: Double?,
    @SerializedName("fat_per_hundred_grams")
    val fatPerHundredGrams: Double?,
    @SerializedName("protein")
    val protein: Double?,
    @SerializedName("protein_per_hundred_grams")
    val proteinPerHundredGrams: Double?,
    @SerializedName("weight")
    val weight: Double?
)
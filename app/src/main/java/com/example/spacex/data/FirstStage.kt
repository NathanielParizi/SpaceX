package com.example.spacex.data


import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("cores")
    val cores: List<Core>
)
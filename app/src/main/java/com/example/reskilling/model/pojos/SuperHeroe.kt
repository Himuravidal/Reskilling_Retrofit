package com.example.reskilling.model.pojos


import com.google.gson.annotations.SerializedName

data class SuperHeroe(
    @SerializedName("appearance")
    val appearance: Appearance,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("connections")
    val connections: Connections,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("name")
    val name: String,
    @SerializedName("powerstats")
    val powerstats: Powerstats,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("work")
    val work: Work
)
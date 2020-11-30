package com.caesar84mx.shared.common.data.networking.heroes.model.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroRaw(
    val name: String,

    @SerialName("realname")
    val realName: String,

    val team: String,

    @SerialName("firstappearance")
    val firstAppearance: String,

    @SerialName("createdby")
    val createdBy: String,

    val publisher: String,

    @SerialName("imageurl")
    val imageUrl: String,

    val bio: String
)
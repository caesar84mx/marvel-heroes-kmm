package com.caesar84mx.shared.common.data.model

data class Hero(
    val id: Long,
    val name: String,
    val realName: String,
    val team: String,
    val firstAppearance: Int,
    val createdBy: String,
    val publisher: String,
    val avatar: String,
    val bio: String
) {
    constructor(): this(0, "", "", "", 0, "", "", "", "")
}
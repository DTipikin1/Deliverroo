package com.deliverroo.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Delivery(
    val phoneNumber: String,
    val name: String,
    val address: String,
    val deliveryComment: String,
    val timestamp: Long,
    val tipReceived: Byte,
    @PrimaryKey val id: Int? = null
)

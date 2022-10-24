package com.deliverroo.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    val phoneNumber: String,
    val name: String,
    val address: String,
    val deliveryComment: String,
    val timesOrdered: Int,
    val averageTip: Byte,
    @PrimaryKey val id: Int? = null

)

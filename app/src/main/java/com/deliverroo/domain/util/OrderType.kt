package com.deliverroo.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}

package com.deliverroo.presentation

import com.deliverroo.domain.models.Delivery
import com.deliverroo.domain.util.DeliveryOrder
import com.deliverroo.domain.util.OrderType

data class MainScreenState(
    val currentDeliveries: List<Delivery> = emptyList(),
    val deliveriesOrder: DeliveryOrder = DeliveryOrder.Time(OrderType.Descending)
)

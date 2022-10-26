package com.deliverroo.domain.util

sealed class DeliveryOrder(val orderType: OrderType) {
    class Time(orderType: OrderType): DeliveryOrder(orderType)
    class TipReceived(orderType: OrderType): DeliveryOrder(orderType)
    class Name(orderType: OrderType):  DeliveryOrder(orderType)
    class PhoneNumber(orderType: OrderType):  DeliveryOrder(orderType)
    class Address(orderType: OrderType):  DeliveryOrder(orderType)
}

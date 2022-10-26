package com.deliverroo.domain.util

sealed class CustomerOrder(val orderType: OrderType) {
    class AverageTip(orderType: OrderType): CustomerOrder(orderType)
    class TimesOrdered(orderType: OrderType): CustomerOrder(orderType)
    class Name(orderType: OrderType): CustomerOrder(orderType)
    class PhoneNumber(orderType: OrderType): CustomerOrder(orderType)
    class Address(orderType: OrderType): CustomerOrder(orderType)
    class DeliveryComment(orderType: OrderType): CustomerOrder(orderType)
}

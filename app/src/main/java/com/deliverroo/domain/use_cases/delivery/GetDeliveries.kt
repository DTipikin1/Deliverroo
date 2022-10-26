package com.deliverroo.domain.use_cases.delivery

import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.models.Delivery
import com.deliverroo.domain.repository.DeliverrooRepository
import com.deliverroo.domain.util.CustomerOrder
import com.deliverroo.domain.util.DeliveryOrder
import com.deliverroo.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDeliveries(
    private val repository: DeliverrooRepository
) {
    operator fun invoke(
        deliveryOrder: DeliveryOrder = DeliveryOrder.Time(OrderType.Descending)
    ): Flow<List<Delivery>> {
        return repository.getDeliveries().map { deliveries ->
            when(deliveryOrder.orderType) {
                is OrderType.Ascending -> {
                    when(deliveryOrder) {
                        is DeliveryOrder.TipReceived -> deliveries.sortedBy { it.tipReceived }
                        is DeliveryOrder.Address -> deliveries.sortedBy { it.address }
                        is DeliveryOrder.Name -> deliveries.sortedBy { it.name }
                        is DeliveryOrder.Time -> deliveries.sortedBy { it.timestamp }
                        is DeliveryOrder.PhoneNumber -> deliveries.sortedBy { it.phoneNumber }
                    }
                }
                is OrderType.Descending -> {
                    when(deliveryOrder) {
                        is DeliveryOrder.TipReceived -> deliveries.sortedByDescending { it.tipReceived }
                        is DeliveryOrder.Address -> deliveries.sortedByDescending { it.address }
                        is DeliveryOrder.Name -> deliveries.sortedByDescending { it.name }
                        is DeliveryOrder.Time -> deliveries.sortedByDescending { it.timestamp }
                        is DeliveryOrder.PhoneNumber -> deliveries.sortedByDescending { it.phoneNumber }
                    }
                }

            }
        }
    }
}
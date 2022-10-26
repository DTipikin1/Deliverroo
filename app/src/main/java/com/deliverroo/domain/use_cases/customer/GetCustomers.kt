package com.deliverroo.domain.use_cases.customer

import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.repository.DeliverrooRepository
import com.deliverroo.domain.util.CustomerOrder
import com.deliverroo.domain.util.DeliveryOrder
import com.deliverroo.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCustomers(
    private val repository: DeliverrooRepository
) {

    operator fun invoke(
        customerOrder: CustomerOrder = CustomerOrder.AverageTip(OrderType.Descending)
    ): Flow<List<Customer>> {
        return repository.getCustomers().map { customers ->
            when(customerOrder.orderType) {
                is OrderType.Ascending -> {
                    when(customerOrder) {
                        is CustomerOrder.AverageTip -> customers.sortedBy { it.averageTip }
                        is CustomerOrder.Address -> customers.sortedBy { it.address }
                        is CustomerOrder.Name -> customers.sortedBy { it.name }
                        is CustomerOrder.TimesOrdered -> customers.sortedBy { it.timesOrdered }
                        is CustomerOrder.PhoneNumber -> customers.sortedBy { it.phoneNumber }
                        is CustomerOrder.DeliveryComment -> customers.sortedBy { it.phoneNumber }
                    }
                }
                is OrderType.Descending -> {
                    when(customerOrder) {
                        is CustomerOrder.AverageTip -> customers.sortedByDescending { it.averageTip }
                        is CustomerOrder.Address -> customers.sortedByDescending { it.address }
                        is CustomerOrder.Name -> customers.sortedByDescending { it.name }
                        is CustomerOrder.TimesOrdered -> customers.sortedByDescending { it.timesOrdered }
                        is CustomerOrder.PhoneNumber -> customers.sortedByDescending { it.phoneNumber }
                        is CustomerOrder.DeliveryComment -> customers.sortedBy { it.phoneNumber }
                    }
                }

            }
        }
    }
}
package com.deliverroo.domain.repository

import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.models.Delivery
import kotlinx.coroutines.flow.Flow

interface DeliverrooRepository {

    fun getCustomers(): Flow<List<Customer>>
    suspend fun getCustomerById(id: Int): Customer?
    suspend fun insertCustomer(customer: Customer)
    suspend fun deleteCustomer(customer: Customer)

    fun getDeliveries(): Flow<List<Delivery>>
    suspend fun getDeliveryById(id: Int): Delivery?
    suspend fun insertDelivery(delivery: Delivery)
    suspend fun deleteDelivery(delivery: Delivery)


}
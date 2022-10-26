package com.deliverroo.data.repository

import com.deliverroo.data.data_source.DeliverrooDao
import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.models.Delivery
import com.deliverroo.domain.repository.DeliverrooRepository
import kotlinx.coroutines.flow.Flow

class DeliverrooRepositoryImpl(
    private val dao: DeliverrooDao
): DeliverrooRepository {
    override fun getCustomers(): Flow<List<Customer>> {
        return dao.getCustomers()
    }
    override suspend fun getCustomerById(id: Int): Customer? {
        return dao.getCustomerById(id)
    }
    override suspend fun insertCustomer(customer: Customer) {
        return dao.insertCustomer(customer)
    }
    override suspend fun deleteCustomer(customer: Customer) {
        return dao.deleteCustomer(customer)
    }


    override fun getDeliveries(): Flow<List<Delivery>> {
        return dao.getDeliveries()
    }
    override suspend fun getDeliveryById(id: Int): Delivery? {
        return dao.getDeliveryById(id)
    }
    override suspend fun insertDelivery(delivery: Delivery) {
        return dao.insertDelivery(delivery)
    }
    override suspend fun deleteDelivery(delivery: Delivery) {
        return dao.deleteDelivery(delivery)
    }
}
package com.deliverroo.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.models.Delivery
import kotlinx.coroutines.flow.Flow

interface DeliverrooDao {

    @Query("SELECT * FROM customer")
    fun getCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customer WHERE id = :id")
    suspend fun getCustomerById(id: Int): Customer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM delivery")
    fun getDeliveries(): Flow<List<Delivery>>

    @Query("SELECT * FROM delivery WHERE id = :id")
    suspend fun getDeliveryById(id: Int): Delivery?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDelivery(delivery: Delivery)

    @Delete
    suspend fun deleteDelivery(delivery: Delivery)
}
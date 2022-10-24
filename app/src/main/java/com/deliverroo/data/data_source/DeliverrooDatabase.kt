package com.deliverroo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.models.Delivery

@Database(
    entities = [
        Customer::class,
        Delivery::class
    ],
    version = 1
)
abstract class DeliverrooDatabase: RoomDatabase() {
    abstract val deliverrooDao: DeliverrooDao
}
package com.deliverroo.di

import android.app.Application
import androidx.room.Room
import com.deliverroo.data.data_source.DeliverrooDatabase
import com.deliverroo.data.repository.DeliverrooRepositoryImpl
import com.deliverroo.domain.repository.DeliverrooRepository
import com.deliverroo.domain.use_cases.customer.CustomerUseCases
import com.deliverroo.domain.use_cases.customer.DeleteCustomer
import com.deliverroo.domain.use_cases.customer.GetCustomers
import com.deliverroo.domain.use_cases.delivery.DeleteDelivery
import com.deliverroo.domain.use_cases.delivery.DeliveryUseCases
import com.deliverroo.domain.use_cases.delivery.GetDeliveries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDeliverrooDatabase(app: Application): DeliverrooDatabase {

        return Room.databaseBuilder(
            app,
            DeliverrooDatabase::class.java,
            DeliverrooDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideDeliverrooRepository(db: DeliverrooDatabase): DeliverrooRepository {
        return DeliverrooRepositoryImpl(db.deliverrooDao)
    }

    @Provides
    @Singleton
    fun provideCustomerUseCases(repository: DeliverrooRepository): CustomerUseCases {
        return CustomerUseCases(
            getCustomers = GetCustomers(repository),
            deleteCustomer = DeleteCustomer(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDeliveryUseCases(repository: DeliverrooRepository): DeliveryUseCases {
        return DeliveryUseCases(
            getDeliveries = GetDeliveries(repository),
            deleteDelivery = DeleteDelivery(repository)
        )
    }
}
package com.deliverroo.domain.use_cases.delivery

import com.deliverroo.domain.models.Delivery
import com.deliverroo.domain.models.InvalidDeliveryException
import com.deliverroo.domain.repository.DeliverrooRepository
import kotlin.jvm.Throws

class AddDelivery(
    private val repository: DeliverrooRepository
) {
    @Throws(InvalidDeliveryException::class)
    suspend operator fun invoke(delivery: Delivery) {
        if (delivery.phoneNumber.isBlank()) {
            throw InvalidDeliveryException("The phone number can't be empty.")
        }
        repository.insertDelivery(delivery)
    }
}
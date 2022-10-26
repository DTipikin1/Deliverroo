package com.deliverroo.domain.use_cases.delivery

import com.deliverroo.domain.models.Delivery
import com.deliverroo.domain.repository.DeliverrooRepository

class DeleteDelivery(
    private val repository: DeliverrooRepository
) {
    suspend operator fun invoke(delivery: Delivery) {
        repository.deleteDelivery(delivery)
    }
}
package com.deliverroo.domain.use_cases.customer

import com.deliverroo.domain.models.Customer
import com.deliverroo.domain.repository.DeliverrooRepository

class DeleteCustomer(
    private val repository: DeliverrooRepository
) {
    suspend operator fun invoke(customer: Customer) {
        repository.deleteCustomer(customer)
    }
}
package com.deliverroo.domain.use_cases.delivery

data class DeliveryUseCases( //to be injects into the viewmodel
    val getDeliveries: GetDeliveries,
    val deleteDelivery: DeleteDelivery
)

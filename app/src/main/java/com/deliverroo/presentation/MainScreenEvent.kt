package com.deliverroo.presentation

import com.deliverroo.domain.models.Delivery

sealed class MainScreenEvent {
    data class DeleteDelivery(val delivery: Delivery): MainScreenEvent()

}

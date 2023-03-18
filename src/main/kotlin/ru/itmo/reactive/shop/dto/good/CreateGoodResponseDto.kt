package ru.itmo.reactive.shop.dto.good

data class CreateGoodResponseDto(
    val id: Long,
    val name: String,
    val price: Float,
    val currency: String
)

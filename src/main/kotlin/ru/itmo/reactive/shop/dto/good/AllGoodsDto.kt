package ru.itmo.reactive.shop.dto.good

data class AllGoodsDto(
    val goods: List<GoodDto>,
    val currency: String
)

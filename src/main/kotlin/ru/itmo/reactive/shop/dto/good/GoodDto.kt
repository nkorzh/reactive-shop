package ru.itmo.reactive.shop.dto.good

import ru.itmo.reactive.shop.model.Good

data class GoodDto(
    val name: String,
    val price: Float
) {
    companion object {
        fun of(good: Good, price: Float) = GoodDto(good.name, price)
    }
}

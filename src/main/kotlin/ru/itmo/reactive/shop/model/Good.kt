package ru.itmo.reactive.shop.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Good(
    val name: String,
    val price: Float,
    @Id
    val id: Long? = null,
)

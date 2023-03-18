package ru.itmo.reactive.shop.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Currency(
    @Id
    val id: Long,
    val name: String
)

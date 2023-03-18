package ru.itmo.reactive.shop.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("\"user\"")
data class User(
    val login: String,
    val currencyId: Long,
    @Id
    val id: Long? = null,
)

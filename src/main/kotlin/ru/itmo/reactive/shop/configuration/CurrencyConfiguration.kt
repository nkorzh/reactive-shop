package ru.itmo.reactive.shop.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

// should be refreshable, token should be retrieved from vault
@ConfigurationProperties("currency")
class CurrencyConfiguration(
    val url: String,
    val token: String
)

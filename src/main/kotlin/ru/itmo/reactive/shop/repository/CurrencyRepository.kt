package ru.itmo.reactive.shop.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.model.Currency

interface CurrencyRepository : ReactiveCrudRepository<Currency, Long> {

    fun findByName(name: String): Mono<Currency>
}

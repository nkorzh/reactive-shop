package ru.itmo.reactive.shop.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.model.Good
import ru.itmo.reactive.shop.repository.GoodRepository

@Service
class GoodService(
    private val goodRepository: GoodRepository
) {

    fun findAll(): Flux<Good> {
        return goodRepository.findAll()
    }

    fun save(good: Good): Mono<Good> {
        return goodRepository.save(good)
    }
}

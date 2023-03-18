package ru.itmo.reactive.shop.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.model.User

interface UserRepository : ReactiveCrudRepository<User, Long> {

    @Query(
        "select c.name from " +
                "\"user\" u left join currency c on u.currency_id = c.id " +
                "where u.id = :id"
    )
    fun getCurrencyNameById(id: Long): Mono<String>
}

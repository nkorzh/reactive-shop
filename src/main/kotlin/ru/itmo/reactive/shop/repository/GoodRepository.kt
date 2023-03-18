package ru.itmo.reactive.shop.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.itmo.reactive.shop.model.Good

interface GoodRepository : ReactiveCrudRepository<Good, Long>

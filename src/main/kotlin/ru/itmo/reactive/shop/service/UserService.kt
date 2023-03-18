package ru.itmo.reactive.shop.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.model.User
import ru.itmo.reactive.shop.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getCurrency(userId: Long): Mono<String> {
        return userRepository.getCurrencyNameById(userId)
    }

    fun save(user: User): Mono<User> {
        return userRepository.save(user)
    }
}

package ru.itmo.reactive.shop.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.dto.user.CreateUserDto
import ru.itmo.reactive.shop.dto.user.CreateUserResponseDto
import ru.itmo.reactive.shop.model.User
import ru.itmo.reactive.shop.service.CurrencyService
import ru.itmo.reactive.shop.service.UserService


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val currencyService: CurrencyService
) {

    @PostMapping("/create")
    fun create(@RequestBody userDto: CreateUserDto): Mono<CreateUserResponseDto> {

        return currencyService.findByName(userDto.currencyName)
            .flatMap { userService.save(User(userDto.login, it.id)) }
            .map { CreateUserResponseDto(it.id!!, it.login, userDto.currencyName) }
    }
}

package ru.itmo.reactive.shop.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.dto.good.AllGoodsDto
import ru.itmo.reactive.shop.dto.good.CreateGoodDto
import ru.itmo.reactive.shop.dto.good.CreateGoodResponseDto
import ru.itmo.reactive.shop.dto.good.GoodDto
import ru.itmo.reactive.shop.model.Good
import ru.itmo.reactive.shop.service.CurrencyService
import ru.itmo.reactive.shop.service.GoodService
import ru.itmo.reactive.shop.service.UserService

@RestController
@RequestMapping("/goods")
class GoodController(
    private val goodService: GoodService,
    private val userService: UserService,
    private val currencyService: CurrencyService
) {

    @GetMapping("{id}/all")
    fun all(@PathVariable id: Long): Mono<AllGoodsDto> {
        return goodService.findAll()
            .collectList()
            .zipWhen { userService.getCurrency(id) }
            .map {
                val prefCurrency = it.t2
                it.mapT1 { goods ->
                    goods.map { good -> GoodDto.of(good, currencyService.convertFromUsd(good.price, prefCurrency)) }
                }
            }
            .map { AllGoodsDto(it.t1, it.t2) }
    }

    @PostMapping("/create")
    fun create(@RequestBody goodCreateDto: CreateGoodDto): Mono<CreateGoodResponseDto> {
        val currencyName = goodCreateDto.currency
        val price = goodCreateDto.price
        val name = goodCreateDto.name

        return currencyService.findByName(currencyName)
            .map { currencyService.convertToUsd(price, it.name) }
            .flatMap { goodService.save(Good(name, it)) }
            .map { CreateGoodResponseDto(it.id!!, it.name, it.price, CurrencyService.DEFAULT_CURRENCY) }
    }
}

package ru.itmo.reactive.shop.service

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.*
import reactor.core.publisher.Mono
import ru.itmo.reactive.shop.configuration.CurrencyConfiguration
import ru.itmo.reactive.shop.dto.external.currency.CurrencyExchangeRates
import ru.itmo.reactive.shop.model.Currency
import ru.itmo.reactive.shop.repository.CurrencyRepository


@Service
class CurrencyService(
    private val currencyRepository: CurrencyRepository,
    private val config: CurrencyConfiguration
) {
    private val webClient: WebClient = builder()
        .baseUrl(config.url)
        .defaultCookie("cookieKey", "cookieValue")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    private val tokenToUsdCourse: MutableMap<String, Float> = mutableMapOf(
        "usd" to 1.0F,
        "eur" to 0.95F,
        "rub" to 72.0F
    )

    @Scheduled(fixedDelayString = "1m")
    fun updateExchangeRates() {
        webClient.get()
            .uri { builder -> builder.queryParam("base", DEFAULT_CURRENCY)
                .build() }
            .header("apikey", config.token)
            .retrieve()
            .bodyToMono(CurrencyExchangeRates::class.java)
            .zipWith(
                currencyRepository.findAll()
                    .map { it.name }
                    .collectList()
            )
            .doOnSuccess {
                val mapRates = it.t1.quotes
                val currencyNames: List<String> = it.t2

                currencyNames.forEach { name -> tokenToUsdCourse[name] = mapRates[name]!! }
            }
            .subscribe()
    }

    fun findByName(token: String): Mono<Currency> {
        return currencyRepository.findByName(token)
    }

    fun convertFromUsd(value: Float, currencyName: String): Float {
        return if (currencyName == DEFAULT_CURRENCY) value
        else tokenToUsdCourse[currencyName]!! * value
    }

    fun convertToUsd(value: Float, srcCurrName: String): Float {
        return if (srcCurrName == DEFAULT_CURRENCY) value
        else value / tokenToUsdCourse[srcCurrName]!!
    }

    companion object {
        const val DEFAULT_CURRENCY = "usd"
    }
}

package ru.itmo.reactive.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.itmo.reactive.shop.configuration.CurrencyConfiguration

@SpringBootApplication
@EnableConfigurationProperties(CurrencyConfiguration::class)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

package pet.ktln.aitgbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class AitgbotApplication

fun main(args: Array<String>) {
	runApplication<AitgbotApplication>(*args)
}

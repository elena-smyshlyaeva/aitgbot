package pet.ktln.aitgbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import pet.ktln.aitgbot.bot.AiTgBot

@Configuration
class TelegramBotConfig {
    @Bean
    fun telegramBotApi(bot: AiTgBot): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
        }
}
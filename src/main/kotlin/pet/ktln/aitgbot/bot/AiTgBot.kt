package pet.ktln.aitgbot.bot

import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Update
import pet.ktln.aitgbot.service.MessageCacheService
import pet.ktln.aitgbot.service.OpenAiService
import pet.ktln.aitgbot.util.createMessage

@Component
class AiTgBot(
    private val openAiService: OpenAiService,
    private val messageCacheService: MessageCacheService,
    commands: Set<BotCommand>,
    @Value("\${telegram.token}")
    token: String
) : TelegramLongPollingCommandBot(token) {
    @Value("\${telegram.bot-name}")
    private val botName: String = ""

    init {
        registerAll(*commands.toTypedArray())
    }

    override fun getBotUsername(): String = botName

    override fun processNonCommandUpdate(update: Update) {
        if (update.hasMessage()) {
            val chatId = update.message.chatId
            if (update.message.hasText()) {
                val messageText = update.message.text
                val messages = messageCacheService.getOrInitMessages(chatId).toMutableList()
                messages += UserMessage(messageText)
                val assistantMessages = openAiService.sendMessages(messages)
                messages += assistantMessages.map { AssistantMessage(it) }
                messageCacheService.saveMessages(chatId, messages)
                assistantMessages.forEach { message ->
                    execute(createMessage(chatId.toString(), message))
                }
            } else {
                execute(createMessage(chatId.toString(), "Я понимаю только текст!"))
            }
        }
    }
}
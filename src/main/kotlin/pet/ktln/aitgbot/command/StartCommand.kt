package pet.ktln.aitgbot.command

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import pet.ktln.aitgbot.util.createMessage
import pet.ktln.aitgbot.service.MessageCacheService

@Component
class StartCommand(private val messageCache: MessageCacheService): BotCommand("/start", "")  {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        messageCache.clearMessages(chat.id)
        absSender.execute(createMessage(chat.id.toString(), "Начинаем диалог!"))
    }
}
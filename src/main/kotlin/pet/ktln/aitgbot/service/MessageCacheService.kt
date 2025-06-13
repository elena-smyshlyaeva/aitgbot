package pet.ktln.aitgbot.service

import org.springframework.ai.chat.messages.Message
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MessageCacheService {
    @Cacheable(cacheNames = ["messages"], key = "#chatId")
    fun getOrInitMessages(chatId: Long): List<Message> = emptyList()

    @CachePut(cacheNames = ["messages"], key = "#chatId")
    fun saveMessages(chatId: Long, messages: List<Message>): List<Message> = messages

    @CacheEvict(cacheNames = ["messages"], key = "#chatId")
    fun clearMessages(chatId: Long): List<Message> = emptyList()
}
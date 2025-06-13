package pet.ktln.aitgbot.service

import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.ai.openai.api.ResponseFormat
import org.springframework.stereotype.Service

@Service
class OpenAiService(private val chatModel: OpenAiChatModel) {
    fun sendMessages(messages: List<Message>): List<String> =
        chatModel.call(Prompt(
            messages,
            OpenAiChatOptions.builder()
                .temperature(0.0)
                .model(OpenAiApi.ChatModel.GPT_4_O)
                .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
                .build()))
            .results?.map { it.output.text ?: ""} ?: emptyList()

}
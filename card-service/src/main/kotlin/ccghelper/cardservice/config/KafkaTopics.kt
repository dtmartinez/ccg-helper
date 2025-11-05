package ccghelper.cardservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean

class KafkaTopics {
    class KafkaTopics {
    @Bean
    fun cardEvents(): NewTopic =
        NewTopic("card.events", 1, 1.toShort())

    @Bean
    fun cardCommands(): NewTopic =
        NewTopic("card.commands", 1, 1.toShort())
}
}
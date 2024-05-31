package itmo.tech.messaging.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private final String deleteQueue = "CAT-DELETE";
    private final String updateQueue = "CAT-UPDATE";
    private final String saveQueue = "CAT-SAVE";

    @Bean
    public Queue queueDelete() {
        return new Queue(deleteQueue);
    }
    @Bean
    public Queue queueUpdate() {
        return new Queue(updateQueue);
    }
    @Bean
    public Queue queueSave() {
        return new Queue(saveQueue);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}

package itmo.tech.messaging.rabbitmq.owner;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitOwnerConfig {
    private final String deleteQueue = "OWNER-DELETE";
    private final String updateQueue = "OWNER-UPDATE";
    private final String saveQueue = "OWNER-SAVE";
    private final String exchange = "OWNER-EXCHANGE";

    @Bean
    public Queue queueOwnerDelete() {
        return new Queue(deleteQueue);
    }

    @Bean
    public Queue queueOwnerUpdate() {
        return new Queue(updateQueue);
    }

    @Bean
    public Queue queueOwnerSave() {
        return new Queue(saveQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter catJsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchangeOwner() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingOwnerUpdate(Queue queueOwnerUpdate, TopicExchange exchange) {
        return BindingBuilder.bind(queueOwnerUpdate).to(exchange).with("rout.owner.update");
    }

    @Bean
    public Binding bindingOwnerDelete(Queue queueOwnerDelete, TopicExchange exchange) {
        return BindingBuilder.bind(queueOwnerDelete).to(exchange).with("rout.owner.delete");
    }

    @Bean
    public Binding bindingOwnerSave(Queue queueOwnerSave, TopicExchange exchange) {
        return BindingBuilder.bind(queueOwnerSave).to(exchange).with("rout.owner.save");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitOwnerTemplate = new RabbitTemplate(connectionFactory);
        rabbitOwnerTemplate.setMessageConverter(catJsonMessageConverter());
        return rabbitOwnerTemplate;
    }
}

package itmo.tech.messaging.rabbitmq.cat;

import itmo.tech.dto.CatDto;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitCatConfig {
    private final String deleteQueue = "CAT-DELETE";
    private final String updateQueue = "CAT-UPDATE";
    private final String saveQueue = "CAT-SAVE";
    private final String exchange = "CAT-EXCHANGE";

    @Bean
    public Queue queueCatDelete() {
        return new Queue(deleteQueue);
    }

    @Bean
    public Queue queueCatUpdate() {
        return new Queue(updateQueue);
    }

    @Bean
    public Queue queueCatSave() {
        return new Queue(saveQueue);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingCatUpdate(Queue queueCatUpdate, TopicExchange exchange) {
        return BindingBuilder.bind(queueCatUpdate).to(exchange).with("rout.cat.update");
    }

    @Bean
    public Binding bindingCatDelete(Queue queueCatDelete, TopicExchange exchange) {
        return BindingBuilder.bind(queueCatDelete).to(exchange).with("rout.cat.delete");
    }

    @Bean
    public Binding bindingCatSave(Queue queueCatSave, TopicExchange exchange) {
        return BindingBuilder.bind(queueCatSave).to(exchange).with("rout.cat.save");
    }

    @Bean
    public RabbitTemplate rabbitCatTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

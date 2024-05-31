package itmo.tech.messaging.rabbitmq.cat;

import itmo.tech.dto.CatDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitCatProducer {
    private final RabbitTemplate rabbitCatTemplate;

    private final String exchange = "CAT-EXCHANGE";

    private final String updateBinding = "rout.cat.update";
    private final String deleteBinding = "rout.cat.delete";
    private final String saveBinding = "rout.cat.save";

    @Autowired
    public RabbitCatProducer(RabbitTemplate rabbitCatTemplate) {
        this.rabbitCatTemplate = rabbitCatTemplate;
    }

    public void sendSaveMessage(CatDto catDto) {
        rabbitCatTemplate.convertAndSend(exchange, saveBinding, catDto);
    }

    public void sendDeleteMessage(Integer catId) {
        rabbitCatTemplate.convertAndSend(exchange, deleteBinding, catId);
    }

    public void sendUpdateMessage(CatDto catDto) {
        rabbitCatTemplate.convertAndSend(exchange, updateBinding, catDto);
    }
}

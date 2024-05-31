package itmo.tech.messaging.rabbitmq.owner;

import itmo.tech.dto.CatDto;
import itmo.tech.dto.OwnerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOwnerProducer {
    private final RabbitTemplate rabbitTemplate;

    private final String exchange = "CAT-EXCHANGE";

    private final String updateBinding = "rout.owner.update";
    private final String deleteBinding = "rout.owner.delete";
    private final String saveBinding = "rout.owner.save";

    @Autowired
    public RabbitOwnerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendSaveMessage(OwnerDto ownerDto) {
        rabbitTemplate.convertAndSend(exchange, saveBinding, ownerDto);
    }

    public void sendDeleteMessage(Integer ownerId) {
        rabbitTemplate.convertAndSend(exchange, deleteBinding, ownerId);
    }

    public void sendUpdateMessage(OwnerDto ownerDto) {
        rabbitTemplate.convertAndSend(exchange, updateBinding, ownerDto);
    }
}

package itmo.tech.messaging.rabbitmq;

import itmo.tech.dto.OwnerDto;
import itmo.tech.mapping.DtoMapper;
import itmo.tech.repositories.OwnerDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {
    private final OwnerDao ownerDao;
    private final DtoMapper dtoMapper;

    @Autowired
    public RabbitConsumer(OwnerDao ownerDao, DtoMapper dtoMapper) {
        this.ownerDao = ownerDao;
        this.dtoMapper = dtoMapper;
    }

    @RabbitListener(queues = "OWNER-SAVE")
    public void receiveSave(OwnerDto ownerDto) {
        ownerDao.save(dtoMapper.toOwner(ownerDto));
    }
    @RabbitListener(queues = "OWNER-UPDATE")
    public void receiveUpdate(OwnerDto ownerDto) {
        ownerDao.save(dtoMapper.toOwner(ownerDto));
    }
    @RabbitListener(queues = "OWNER-DELETE")
    public void receiveUpdate(Integer id) {
        ownerDao.deleteById(id);
    }
}

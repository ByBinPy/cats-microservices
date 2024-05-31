package itmo.tech.messaging.rabbitmq;

import itmo.tech.dto.CatDto;
import itmo.tech.mapping.DtoMapper;
import itmo.tech.repositories.CatDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {
    private final CatDao catDao;
    private final DtoMapper dtoMapper;

    @Autowired
    public RabbitConsumer(CatDao catDao, DtoMapper dtoMapper) {
        this.catDao = catDao;
        this.dtoMapper = dtoMapper;
    }

    @RabbitListener(queues = "CAT-SAVE")
    public void receiveSave(CatDto catDto) {
        catDao.save(dtoMapper.toCat(catDto));
    }
    @RabbitListener(queues = "CAT-UPDATE")
    public void receiveUpdate(CatDto catDto) {
        catDao.save(dtoMapper.toCat(catDto));
    }
    @RabbitListener(queues = "CAT-DELETE")
    public void receiveUpdate(Integer id) {
        catDao.deleteById(id);
    }
}

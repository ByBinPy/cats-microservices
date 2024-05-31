package itmo.tech.messaging.rpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import itmo.tech.dto.CatShow;
import itmo.tech.messaging.mapping.DtoMapper;
import itmo.tech.owner.CatServiceGrpc;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatRpcClient {
    private CatServiceGrpc.CatServiceBlockingStub blockingStub;
    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8084)
                .usePlaintext()
                .build();
        blockingStub = CatServiceGrpc.newBlockingStub(managedChannel);
    }

    public CatShow findCatById(Integer catId, Integer ownerId) {
        return DtoMapper.toCatShow(blockingStub.findCatById(DtoMapper.toSecurityId(catId, ownerId)));
    }
    public List<CatShow> findCatsByColor(String color, Integer ownerId) {
        return blockingStub
                .findCatsByColor(DtoMapper.toSecurityColor(color, ownerId))
                .getCatsList().stream().map(DtoMapper::toCatShow)
                .collect(Collectors.toList());
    }
}

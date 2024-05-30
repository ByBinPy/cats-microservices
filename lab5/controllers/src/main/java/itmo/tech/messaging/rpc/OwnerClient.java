package itmo.tech.messaging.rpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import itmo.tech.owner.OwnerServiceGrpc;
import itmo.tech.dto.OwnerDto;
import itmo.tech.messaging.mapping.DtoMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class OwnerClient {
    private OwnerServiceGrpc.OwnerServiceBlockingStub blockingStub;
    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8082)
                .usePlaintext()
                .build();
        blockingStub = OwnerServiceGrpc.newBlockingStub(managedChannel);
    }
    public OwnerDto findOwnerById(Integer ownerId) {
        return DtoMapper
                .toOwnerDto(blockingStub
                .findOwnerById(DtoMapper.toRpcOwnerId(ownerId)));
    }
    public OwnerDto findOwnerByName(String name) {
        return DtoMapper
                .toOwnerDto(blockingStub
                        .findOwnerByName(DtoMapper.toRpcOwnerName(name)));
    }
}

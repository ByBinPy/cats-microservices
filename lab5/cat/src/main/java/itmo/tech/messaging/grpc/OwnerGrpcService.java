package itmo.tech.messaging.grpc;

import itmo.tech.mapping.DtoMapper;
import itmo.tech.owner.OwnerServiceGrpc;
import itmo.tech.repositories.OwnerDao;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OwnerGrpcService extends OwnerServiceGrpc.OwnerServiceImplBase {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerGrpcService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public void findOwnerById(itmo.tech.owner.Rpc.OwnerId request,
                             io.grpc.stub.StreamObserver<itmo.tech.owner.Rpc.OwnerDto> responseObserver) {
        responseObserver.onNext(DtoMapper.toRpcOwnerDto(ownerDao.findById(request.getId()).get()));
    }
}

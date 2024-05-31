package itmo.tech.messaging.rpc;

import io.grpc.stub.StreamObserver;
import itmo.tech.entities.Owner;
import itmo.tech.mapping.DtoMapper;
import itmo.tech.owner.OwnerServiceGrpc;
import itmo.tech.owner.Rpc;
import itmo.tech.repositories.OwnerDao;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OwnerGrpcService extends OwnerServiceGrpc.OwnerServiceImplBase {

    private final OwnerDao ownerDao;
    private final DtoMapper dtoMapper;

    @Autowired
    public OwnerGrpcService(OwnerDao ownerDao, DtoMapper dtoMapper) {
        this.ownerDao = ownerDao;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public void findOwnerById(itmo.tech.owner.Rpc.Id request,
                              io.grpc.stub.StreamObserver<itmo.tech.owner.Rpc.OwnerDto> responseObserver) {
        try {
            Rpc.OwnerDto ownerDto = dtoMapper.toRpcOwnerDto(ownerDao.findById(request.getId()).get());
            responseObserver.onNext(ownerDto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void findOwnerByName(Rpc.OwnerName request,
                                StreamObserver<Rpc.OwnerDto> responseObserver) {
        try {
            Owner owner = ownerDao.findOwnerByName(request.getName()).get();
            Rpc.OwnerDto ownerDto = dtoMapper.toRpcOwnerDto(owner);
            responseObserver.onNext(ownerDto);
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}

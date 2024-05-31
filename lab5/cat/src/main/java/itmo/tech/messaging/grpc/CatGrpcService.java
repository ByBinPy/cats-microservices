package itmo.tech.messaging.grpc;

import io.grpc.stub.StreamObserver;
import itmo.tech.enums.Colors;
import itmo.tech.mapping.DtoMapper;
import itmo.tech.owner.CatServiceGrpc;
import itmo.tech.owner.Rpc;
import itmo.tech.repositories.CatDao;
import itmo.tech.repositories.OwnerDao;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class CatGrpcService extends CatServiceGrpc.CatServiceImplBase {
    private final CatDao catDao;
    private final OwnerDao ownerDao;
    private final DtoMapper dtoMapper;

    @Autowired
    public CatGrpcService(CatDao catDao, OwnerDao ownerDao, DtoMapper dtoMapper) {
        this.catDao = catDao;

        this.ownerDao = ownerDao;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public void findCatById(Rpc.SecurityId request,
                            StreamObserver<Rpc.CatDto> responseObserver) {
        try {
            Rpc.CatDto response = dtoMapper.toRpcDto(catDao.findById(request.getCatId()).get());
            if (ownerDao.findById(request.getOwnerId()).isPresent()) {
                responseObserver.onNext(response);
            } else {
                responseObserver.onNext(Rpc.CatDto.newBuilder().build());
            }
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(e);
        }
    }
    @Override
    public void findCatsByColor(Rpc.SecurityColor request,
                                StreamObserver<Rpc.Cats> listStreamObserver) {
        try{
            Rpc.Cats cats = dtoMapper.toRpcCats(catDao.findCatsByColor(Colors.valueOf(request.getColor())));
            listStreamObserver.onNext(cats);
            listStreamObserver.onCompleted();
        }
        catch (Exception e) {
            listStreamObserver.onError(e);
        }

    }
}

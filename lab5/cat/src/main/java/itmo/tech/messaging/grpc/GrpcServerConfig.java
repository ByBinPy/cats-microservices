package itmo.tech.messaging.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import itmo.tech.mapping.DtoMapper;
import itmo.tech.repositories.CatDao;
import itmo.tech.repositories.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {
    private final CatDao catDao;
    private final OwnerDao ownerDao;

    private final DtoMapper dtoMapper;

    @Autowired
    public GrpcServerConfig(CatDao catDao, OwnerDao ownerDao, DtoMapper dtoMapper) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
        this.dtoMapper = dtoMapper;
    }

    @Bean
    public Server grpcServer() throws IOException {
        Server server = ServerBuilder
                .forPort(8084)
                .addService(new CatGrpcService(catDao, ownerDao, dtoMapper))
                .build();
        server.start();
        return server;
    }
}
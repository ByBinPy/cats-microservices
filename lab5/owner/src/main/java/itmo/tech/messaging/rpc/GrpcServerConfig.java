package itmo.tech.messaging.rpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import itmo.tech.repositories.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {


    private final OwnerDao ownerDao;

    @Autowired
    public GrpcServerConfig(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Bean
    public Server grpcServer() throws IOException {
        Server server = ServerBuilder
                .forPort(8082)
                .addService(new OwnerGrpcService(ownerDao))
                .build();
        server.start();
        return server;
    }
}
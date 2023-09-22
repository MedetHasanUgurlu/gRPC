package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.BankService;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        // Creating server
        final Server server = ServerBuilder.forPort(6565).addService(new BankService()).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

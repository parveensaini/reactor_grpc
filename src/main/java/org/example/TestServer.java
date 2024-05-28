package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class TestServer {
  public static void main(String[] args) throws InterruptedException, IOException {
    // Start the server
    Server server =
        ServerBuilder.forPort(8888).addService(new ExampleService.GrpcService()).build().start();
    server.awaitTermination();
  }
}

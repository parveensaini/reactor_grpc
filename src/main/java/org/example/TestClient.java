package org.example;

import com.parveen.reactivegrpc.HelloRequest;
import com.parveen.reactivegrpc.ReactorGreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestClient {

  private ManagedChannel channel;

  public void processRequest() {
    for (int i = 0; i < 10000000; ++i) {
      ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
      System.out.println(
          stub.sayHello(HelloRequest.newBuilder().setName("Name").build()).log().subscribe());
    }
  }

  private void init() {
    channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
  }

  public static void main(String[] args) {
    TestClient testClient = new TestClient();
    testClient.init();
    testClient.processRequest();
  }
}

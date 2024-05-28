package org.example;

import com.parveen.reactivegrpc.HelloRequest;
import com.parveen.reactivegrpc.HelloResponse;
import com.parveen.reactivegrpc.ReactorGreeterGrpc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExampleService {

  public static class GrpcService extends ReactorGreeterGrpc.GreeterImplBase {

    private HelloResponse greet(String s, HelloRequest request) {
      return HelloResponse.newBuilder().setMessage(s + request.getName()).build();
    }

    private HelloResponse greet(String s, String request) {
      return HelloResponse.newBuilder().setMessage(s + request).build();
    }

    @Override
    public Mono<HelloResponse> sayHello(Mono<HelloRequest> request) {
      return request.map(protoRequest -> greet("Hello", protoRequest));
    }

    @Override
    public Flux<HelloResponse> sayHelloBothStream(Flux<HelloRequest> request) {
      return request
          .map(HelloRequest::getName)
          .buffer(2)
          .map(names -> greet("Hello", String.join(" and ", names)));
    }
  }
}

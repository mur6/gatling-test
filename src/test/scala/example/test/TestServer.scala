package example.test

import io.grpc.{Server, ServerBuilder}
import scala.concurrent.{ExecutionContext, Future}
//import scala.util.{Random, Try}

object TestServer {
  def startServer(): Server = {
    val greetService = new GreeterServiceGrpc.GreeterService {
      override def sayHello(request: HelloRequest) = Future.successful {
        HelloReply(s"Server says: Hello ${request.name}!")
      }
    }

    val port = 50111
    val server: Server = ServerBuilder.forPort(port)
      .addService(GreeterServiceGrpc.bindService(greetService, ExecutionContext.global))
      .build.start

    server
  }
}

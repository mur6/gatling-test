package example

import io.grpc.{Server, ServerBuilder}
import example.test._
import scala.concurrent.{ExecutionContext, Future}


object TestServer {
  def startServer(): Server = {
    val greetService = new GreeterServiceGrpc.GreeterService {
      override def sayHello(request: HelloRequest) = Future.successful {
        HelloReply(s"Server says: Hello ${request.name}!")
      }
    }

    val port = 50111
    val server = ServerBuilder.forPort(port)
      .addService(GreeterServiceGrpc.bindService(greetService, ExecutionContext.global)).build.start

    server
  }

  def main(args: Array[String]): Unit = {
    val server = startServer()
    server.awaitTermination()
  }
}

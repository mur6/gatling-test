package example

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.grpc.{ManagedChannelBuilder, Status}
import scala.concurrent.duration._
import example.test.{GreeterServiceGrpc, HelloRequest}


class MySimu extends Simulation {
  val grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 50111).usePlaintext())

  val computerDbScn = scenario("My Scenario")
  .exec(
    grpc("my_request")
      .rpc(GreeterServiceGrpc.METHOD_SAY_HELLO)
      .payload(HelloRequest(name="abc"))
  ).pause(100 milliseconds)

  setUp(computerDbScn.inject(
    //constantUsersPerSec(2) during(1 minute)
    atOnceUsers(1),
  ).protocols(grpcConf))
}

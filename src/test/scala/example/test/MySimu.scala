package example.test

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import com.github.phisgr.gatling.util._
// stringToExpression is hidden because we have $ in GrpcDsl
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.gatling.core.session.Expression
import io.grpc.{ManagedChannelBuilder, Status}
import scala.concurrent.duration._


class MySimu extends Simulation {
  val grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext())

  val computerDbScn = scenario("My Scenario")
  .exec(
    grpc("my_request")
      .rpc(GreeterServiceGrpc.METHOD_SAY_HELLO)
      .payload(HelloRequest(name="abc"))
  ).exitHereIfFailed

  setUp(computerDbScn.inject(
    //constantUsersPerSec(2) during(1 minute)
    atOnceUsers(1),
  ).protocols(grpcConf))
}

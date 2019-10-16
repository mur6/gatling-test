import example.test.{GreeterServiceGrpc, HelloRequest}

import com.github.phisgr.gatling.grpc.Predef._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ComputerWorld extends Simulation {
  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("""text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8""")
    .acceptEncodingHeader("""gzip, deflate""")
    .acceptLanguageHeader("""en-gb,en;q=0.5""")
    .userAgentHeader("""Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:31.0) Gecko/20100101 Firefox/31.0""")

  val computerDbScn = scenario("Computer Scenario")
  .exec(
    grpc("my_request")
      .rpc(GreeterServiceGrpc.METHOD_SAY_HELLO)
      .payload(HelloRequest(name="abc"))
  )

  setUp(computerDbScn.inject(
    constantUsersPerSec(2) during(1 minute)
  ).protocols(httpProtocol))
}

package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class GetAllSymbolsInfoSimulation extends Simulation {

  //http conf

  var httpConf =  http.baseUrl("https://api.binance.com/api")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val sentHeaders = Map(
    "Content-Type" -> "application/javascript",
    "Accept" -> "text/html"
  )

  //scenario

  var scen = scenario("GetAllSymbolsInfo")
    .exec(http("GetAllInfo")
      .get("/v3/ticker/24hr")
      .headers(sentHeaders)
      .check(status is 200))

  // set up
  setUp(scen.inject(atOnceUsers(15))).protocols(httpConf)
}
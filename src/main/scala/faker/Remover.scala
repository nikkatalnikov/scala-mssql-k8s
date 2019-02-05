package faker

import faker.CountriesRepository.CountriesRepository
import faker.FakeTransactionsRepository._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Remover extends App {

  val fakeTransactionsRepository = new FakeTransactionsRepository()
  val f2 = fakeTransactionsRepository.dropTable()
  Await.result(f2, Duration.Inf)

  val countriesRepository = new CountriesRepository()
  val f1 = countriesRepository.dropTable()
  Await.result(f1, Duration.Inf)

}


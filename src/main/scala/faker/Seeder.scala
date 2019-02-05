package faker

import faker.CountriesRepository._
import faker.FakeTransactionsRepository._

import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.util.Random


object Seeder extends App {

  val countriesRepository = new CountriesRepository()
  val f0 = countriesRepository.createTable()
  Await.result(f0, Duration.Inf)

  val f1 = countriesRepository.bulkInsert(CountriesDict.asList)
  Await.result(f1, Duration.Inf)

  val fakeTransactionsStream = Stream
    .continually()
    .map(_ => {
      val card = fabricator.Finance().visaCard
      val iban = fabricator.Finance().iban
      val amount = fabricator.Alphanumeric().randomDouble(100, 1000)
      val randCountry = Random.nextInt(CountriesDict.asList.size - 1) + 1

      FakeTransaction(None, card, iban, amount, randCountry)

    })
    .take(1000)

  val fakeTransactionsRepository = new FakeTransactionsRepository()
  val f2 = fakeTransactionsRepository.createTable()
  Await.result(f2, Duration.Inf)

  val f3 = fakeTransactionsRepository.bulkInsert(fakeTransactionsStream.toList)
  Await.result(f3, Duration.Inf)

}


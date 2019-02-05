package faker

import CountriesRepository.Countries
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Future

object FakeTransactionsRepository {
  case class FakeTransaction(id: Option[Int], card: String, iban: String, amount: Double, country_id: Int)

  class FakeTransactions(tag: Tag) extends Table[FakeTransaction](tag, "transactions") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def card = column[String]("card")
    def iban = column[String]("iban")
    def country_id = column[Int]("country_id")
    def amount  = column[Double]("amount")
    def supplier = foreignKey("SUP_FK", country_id, TableQuery[Countries])(_.id)

    def * = (id.?, card, iban, amount, country_id) <> (FakeTransaction.tupled, FakeTransaction.unapply)
  }

  class FakeTransactionsRepository {
    val query: TableQuery[FakeTransactions] = TableQuery[FakeTransactions]
    def createTable() : Future[Any] = DB.db.run(query.schema.create)
    def bulkInsert(transactions: List[FakeTransaction]): Future[Any] = DB.db.run(query ++= transactions)
    def dropTable() : Future[Any] = {
      lazy val dropQ = sqlu"""drop table if exists transactions"""
      DB.db.run(dropQ)
    }
  }

}

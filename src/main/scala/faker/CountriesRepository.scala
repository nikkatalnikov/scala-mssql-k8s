package faker

import scala.concurrent.Future
import slick.jdbc.SQLServerProfile.api._

object CountriesRepository {
  case class Country(id: Option[Int], name: String)

  class Countries(tag: Tag) extends Table[Country](tag, "countries") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (id.?, name) <> (Country.tupled, Country.unapply)
  }

  class CountriesRepository {
    val query: TableQuery[Countries] = TableQuery[Countries]
    def createTable() : Future[Any] = DB.db.run(query.schema.create)
    def bulkInsert(countries: List[String]): Future[Any] = DB.db.run(query ++= countries.map(x => Country(None, x)))
    def dropTable() : Future[Any] = {
      lazy val dropQ = sqlu"""drop table if exists countries"""
      DB.db.run(dropQ)
    }

  }

}

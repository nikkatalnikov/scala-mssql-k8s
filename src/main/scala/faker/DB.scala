package faker

import slick.jdbc.SQLServerProfile
import slick.basic.DatabaseConfig

object DB {
  val dbConfig: DatabaseConfig[SQLServerProfile] = DatabaseConfig.forConfig("sqlserver")
  val db: SQLServerProfile#Backend#Database = dbConfig.db
}

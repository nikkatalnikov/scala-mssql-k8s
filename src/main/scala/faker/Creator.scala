package faker

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import slick.jdbc.SQLServerProfile.api._

object Creator extends App {
  lazy val createQ = sqlu"""create database fake"""
  val f = DB.db.run(createQ)
  Await.result(f, Duration.Inf)
}

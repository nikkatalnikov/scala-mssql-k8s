name := "faker_seed"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += "Fabricator" at "http://dl.bintray.com/biercoff/Fabricator"
libraryDependencies += "com.github.azakordonets" % "fabricator_2.12" % "2.1.5"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "7.2.0.jre11" % Test
)

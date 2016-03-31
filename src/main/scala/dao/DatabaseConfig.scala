package com.vinctus.venatus.dao

trait DatabaseConfig {
  val driver = slick.driver.PostgresDriver

  import driver.api._

  def db = Database.forConfig("postgres")

  implicit val session: Session = db.createSession()
}
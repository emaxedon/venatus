package com.vinctus.venatus.dao

import slick.backend.DatabaseConfig
import slick.driver.PostgresDriver

trait DB {
  import PostgresDriver.api._

  private val dbConfig: DatabaseConfig[PostgresDriver] = DatabaseConfig.forConfig("postgres")

	val db = dbConfig.db
}
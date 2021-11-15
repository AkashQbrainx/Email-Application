package com.qbrainx.config

import slick.basic.DatabaseConfig
import slick.jdbc.{JdbcBackend, JdbcProfile}
object SlickConfig {
  val dataConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("slick",AppConfig.slickConfig)

  val jdbcProfile: JdbcProfile = dataConfig.profile

  val db: JdbcBackend#DatabaseDef = dataConfig.db

}

package com.vinctus.venatus.dao

import scala.concurrent.{Future, Await}
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.UUID
import java.sql.Timestamp
import slick.driver.PostgresDriver.api._
import com.vinctus.venatus.models.Role

class RolesTable(tag: Tag) extends Table[Role](tag, "roles") {

  def id = column[UUID]("id")
  def name = column[String]("name", O.PrimaryKey)
  def createdAt = column[Timestamp]("created_at")
  def updatedAt = column[Timestamp]("updated_at")

  def idIdx = index("roles_id_index", id, unique = true)

  def nameIdx = index("roles_name_index", name, unique = true)

  def * = (id, name, createdAt, updatedAt) <> ((Role.apply _).tupled, Role.unapply _)
}

class RoleDAO extends DB {

  private val Roles = TableQuery[RolesTable]

  def list: Future[Seq[Role]] = db.run(Roles.result)

  def find(id: UUID): Future[Option[Role]] = {
    db.run(Roles.filter(_.id === id).result.headOption)
  }

  def find(name: String): Future[Option[Role]] = {
    db.run(Roles.filter(_.name === name).result.headOption)
  }
  
  def create(role: Role): Future[Option[Role]] = {
    db.run((Roles += role).asTry).flatMap(_ match {
      case Success(result) => find(role.id)
      case Failure(e) => Future(None)
    })
  }

  def update(role: Role): Future[Option[Role]] = {
    db.run(Roles.filter(_.id === role.id).map(u => u).update(role).asTry).flatMap(_ match {
      case Success(result) if result == 1 => find(role.id)
      case Failure(e) => Future(None)
    })
  }

  def delete(id: UUID): Future[Int] = db.run(Roles.filter(_.id === id).delete)
}
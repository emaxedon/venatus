package com.vinctus.venatus.dao

import scala.concurrent.{Future, Await}
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

import java.util.UUID
import java.sql.Timestamp

import org.mindrot.jbcrypt._

import slick.driver.PostgresDriver.api._

import models.User

class UserDAO extends DatabaseConfig {

  private val Users = TableQuery[UsersTable]
  private val UsersRoles = TableQuery[UsersRolesTable]

  def list: Future[Seq[User]] = db.run(Users.result)

  def find(id: UUID): Future[Option[User]] = {
    db.run(Users.filter(_.id === id).result.headOption)
  }

  def find(email: String): Future[Option[User]] = {
    db.run(Users.filter(_.email === email).result.headOption)
  }
  
  def create(user: User): Future[Option[User]] = {
    val pwHash = BCrypt.hashpw(user.password, BCrypt.gensalt())

    db.run((Users += user.patch(password = Some(pwHash))).asTry).flatMap(_ match {
      case Success(result) => find(user.id)
      case Failure(e) => Future(None)
    })
  }

  def update(user: User): Future[Option[User]] = {
    db.run(Users.filter(_.id === user.id)
      .map(u => (u.email, u.firstName, u.lastName))
      .update(user.email, user.firstName, user.lastName).asTry)
      .flatMap(_ match {
        case Success(result) if result == 1 => find(user.id)
        case Failure(e) => Future(None)
      })
  }

  def delete(id: UUID): Future[Int] = db.run(Users.filter(_.id === id).delete)

  private class UsersTable(tag: Tag) extends Table[User](tag, "users") {

    def id = column[UUID]("id")
    def email = column[String]("email", O.PrimaryKey)
    def password = column[String]("password")
    def firstName = column[Option[String]]("first_name")
    def lastName = column[Option[String]]("last_name")
    def createdAt = column[Timestamp]("created_at")
    def updatedAt = column[Timestamp]("updated_at")

    def idIdx = index("users_id_index", id, unique = true)

    def emailIdx = index("users_email_index", email, unique = true)

    def * = (id, email, password, firstName, lastName, createdAt, updatedAt) <> ((User.apply _).tupled, User.unapply _)
  }

  private class UsersRolesTable(tag: Tag) extends Table[(UUID, UUID)](tag, "users_roles") {

    private val Users = TableQuery[UsersTable]
    private val Roles = TableQuery[RolesTable]

    def userId = column[UUID]("user_id")
    def roleId = column[UUID]("role_id")

    def pk = primaryKey("users_roles_pk", (userId, roleId))

    def user = foreignKey("user_fk", userId, Users)(_.id, onDelete=ForeignKeyAction.Cascade)
    def role = foreignKey("role_fk", roleId, Roles)(_.id, onDelete=ForeignKeyAction.Cascade)

    def * = (userId, roleId)
  }
}
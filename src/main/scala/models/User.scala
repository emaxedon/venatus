package com.vinctus.venatus.models

import java.util.UUID
import java.sql.Timestamp
import java.util.Date

case class User(
	id: UUID = UUID.randomUUID, 
	email: String,
	password: String,
	firstName: Option[String] = None,
	lastName: Option[String] = None,
	createdAt: Timestamp = new Timestamp(new Date().getTime),
	updatedAt: Timestamp = new Timestamp(new Date().getTime)) {

	def patch(
		email: Option[String] = None,
		firstName: Option[String] = None,
		lastName: Option[String] = None,
		password: Option[String] = None): User = this.copy(
			email = email.getOrElse(this.email),
			password = password.getOrElse(this.password),
			firstName = if (firstName.isDefined) firstName else this.firstName,
			lastName = if (lastName.isDefined) lastName else this.lastName,
			updatedAt = new Timestamp(new Date().getTime))
}
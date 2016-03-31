package com.vinctus.venatus.models

import java.util.UUID
import java.sql.Timestamp
import java.util.Date

case class Role(
	id: UUID = UUID.randomUUID,
	name: String,
	createdAt: Timestamp = new Timestamp(new Date().getTime),
	updatedAt: Timestamp = new Timestamp(new Date().getTime)) {

	def patch(
		name: Option[String] = None): Role = this.copy(
			name = name.getOrElse(this.name),
			updatedAt = new Timestamp(new Date().getTime))
}
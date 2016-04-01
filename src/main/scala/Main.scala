package com.vinctus.venatus

import akka.actor.{ ActorSystem, Props }
import servers._

import scala.concurrent.ExecutionContext.Implicits.global
import com.vinctus.venatus.dao.UserDAO
import com.vinctus.venatus.models.User

object Main extends App {

	val userDAO = new UserDAO

	userDAO.find("emaxedon@gmail.com").map({
		case Some(user) =>
		case None =>
			val admin = new User(
				email = "emaxedon@gmail.com", 
				password = "adminpass",
				firstName = Some("Edward"),
				lastName = Some("Maxedon"))

			userDAO.create(admin)
	})

	val system = ActorSystem("servers")
	val server = system.actorOf(Props[LoginServer], "LoginServer")

}
package com.vinctus.venatus

import akka.actor.{ ActorSystem, Props }
import servers._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import com.vinctus.venatus.dao.UserDAO
import com.vinctus.venatus.models.User

object Main extends App {

	val userDAO = new UserDAO

	Await.result(
		userDAO.find("emaxedon@gmail.com").map({
			case None =>
				val admin = new User(
					email = "emaxedon@gmail.com", 
					password = "adminpass",
					firstName = Some("Edward"),
					lastName = Some("Maxedon"))

				userDAO.create(admin)
			case Some(user) =>
		}), 5 seconds)

	val system = ActorSystem("servers")
	val server = system.actorOf(Props[LoginServer], "LoginServer")

}
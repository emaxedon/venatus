package com.vinctus.venatus

import akka.actor.{ ActorSystem, Props }
import servers._

object Main extends App {

	val system = ActorSystem("servers")
	val server = system.actorOf(Props[LoginServer], "LoginServer")

}
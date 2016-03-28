package com.vinctus.venatus

import akka.actor.{ ActorSystem, Props }
import server._

object Main extends App {

	val system = ActorSystem("server")
	val server = system.actorOf(Props[Server], "ServerActor")

}
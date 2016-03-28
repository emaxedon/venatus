package com.vinctus.venatus

import akka.actor.{ ActorSystem, Props }
import server._
import com.vinctus.venatus.protobuf.MovementProtos.Move

object Main extends App {

	val system = ActorSystem("server")
	val server = system.actorOf(Props[Server], "ServerActor")

}
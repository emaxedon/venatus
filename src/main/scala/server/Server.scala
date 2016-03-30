package com.vinctus.venatus.server

import akka.actor.{ Actor, Props }
import akka.io.{ IO, Tcp }
import java.net.InetSocketAddress
import com.vinctus.venatus.handler._

class Server extends Actor {

  import Tcp._
  import context.system
 
  IO(Tcp) ! Tcp.Bind(self, new InetSocketAddress("localhost", 6666))

  def receive = {
    case b @ Tcp.Bound(localAddress) =>
      println(s"Server: bound at $localAddress")
 
    case Tcp.CommandFailed(_: Bind) =>
      println("Server: failed to bind")
      context.stop(self)
 
    case c @ Tcp.Connected(remote, local) =>
      println(s"Server: connection made to server from $remote")
      val handler = context.actorOf(Props[PacketHandler])
      val connection = sender
      connection ! Tcp.Register(handler)
  }

}
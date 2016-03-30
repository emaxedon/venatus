package com.vinctus.venatus.server

import akka.actor.{ Actor, Props }
import akka.io.{ IO, Tcp }
import java.net.InetSocketAddress
import com.vinctus.venatus.handler.LoginHandler

class LoginServer extends Actor {

  import Tcp._
  import context.system
 
  IO(Tcp) ! Tcp.Bind(self, new InetSocketAddress("localhost", 6666))

  def receive = {
    case b @ Tcp.Bound(localAddress) =>
      println(s"LoginServer: bound at $localAddress")
 
    case Tcp.CommandFailed(_: Bind) =>
      println("LoginServer: failed to bind")
      context.stop(self)
 
    case c @ Tcp.Connected(remote, local) =>
      println(s"LoginServer: connection made to server from $remote")
      val handler = context.actorOf(Props[LoginHandler])
      val connection = sender
      connection ! Tcp.Register(handler)
  }

}
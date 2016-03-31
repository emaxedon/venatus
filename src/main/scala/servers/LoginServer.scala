package com.vinctus.venatus.servers

import akka.actor.{ Actor, Props }
import akka.io.{ IO, Tcp }
import java.net.InetSocketAddress
import com.vinctus.venatus.handlers.LoginHandler

class LoginServer extends Actor {

  import Tcp._
  import context.system
 
  IO(Tcp) ! Tcp.Bind(self, new InetSocketAddress("localhost", 6666))

  def receive = { 
    case Tcp.CommandFailed(_: Bind) =>
      context.stop(self)

    case c @ Tcp.Connected(remote, local) =>
      val handler = context.actorOf(Props[LoginHandler])
      val connection = sender
      connection ! Tcp.Register(handler)
  }

}
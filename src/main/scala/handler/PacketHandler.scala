package com.vinctus.venatus.handler

import akka.actor.{ Actor, ActorRef, Props }
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import akka.io.Tcp.{ Write, Received }

class PacketHandler extends Actor {

  def receive: Receive = {
    case Tcp.Received(data) =>
      println("PacketHandler: received data")
      sender ! Tcp.Write(data)
    case _: Tcp.ConnectionClosed =>
      println("PacketHandler: connection has been closed")
      context.stop(self)
  }
}
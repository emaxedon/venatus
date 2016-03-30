package com.vinctus.venatus.handler

import akka.actor.{ Actor, ActorRef, Props }
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import akka.io.Tcp.{ Write, Received }
import com.vinctus.venatus.protobuf.LoginProtos._

class LoginHandler extends Actor {

  def receive: Receive = {
    case Tcp.Received(data) =>
      val dataString = (new String(data.toArray)).trim
      val dataBytes: Array[Byte] = dataString.split(",").map(_.toByte)
      val wrapperMessage = WrapperMessage.parseFrom(dataBytes)

      println(wrapperMessage)

      // val wrapperMessage = WrapperMessage(???)
      // val wrapperMessageBytes = wrapperMessage.toByteArray
      // val byteString = ByteString(wrapperMessageBytes.mkString(","))
      // sender ! Tcp.Write(byteString)

    case _: Tcp.ConnectionClosed =>
      context.stop(self)
  }
}
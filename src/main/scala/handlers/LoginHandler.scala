package com.vinctus.venatus.handlers

import akka.actor.{ Actor, ActorRef, Props }
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import akka.io.Tcp.{ Write, Received }
import com.vinctus.venatus.protobuf.LoginProtos._
import com.vinctus.venatus.protobuf.LoginProtos.WrapperMessage.Msg

class LoginHandler extends Actor {

  def receive: Receive = {
    case Tcp.Received(data) =>
      val dataString = (new String(data.toArray)).trim
      val dataBytes: Array[Byte] = dataString.split(",").map(_.toByte)
      val wrapperMessage = WrapperMessage.parseFrom(dataBytes)
      
      wrapperMessage.msg match {
        case Msg.Login(l) =>
          println("Found login message")
        case Msg.Register(r) =>
          println("Found register message")
        case Msg.Empty => 
          println("Failed to match message")
      }

      // val login = Login(email = "emaxedon@gmail.com", password = "adminpass")
      // val wrapperMessage = WrapperMessage().withLogin(login)
      // val wrapperMessageBytes = wrapperMessage.toByteArray
      // val byteString = ByteString(wrapperMessageBytes.mkString(","))
      // sender ! Tcp.Write(byteString)

    case _: Tcp.ConnectionClosed =>
      context.stop(self)
  }
}
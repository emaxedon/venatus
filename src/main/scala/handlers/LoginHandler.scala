package com.vinctus.venatus.handlers

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{ Actor, ActorRef, Props }
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import akka.io.Tcp.{ Write, Received }
import org.mindrot.jbcrypt._
import com.vinctus.venatus.protobuf.LoginProtos._
import com.vinctus.venatus.protobuf.LoginProtos.WrapperMessage.Msg
import com.vinctus.venatus.models.User
import com.vinctus.venatus.dao.UserDAO

class LoginHandler extends Actor {

  val userDAO = new UserDAO

  def receive: Receive = {
    case Tcp.Received(data) =>
      val dataString = (new String(data.toArray)).trim
      val dataBytes: Array[Byte] = dataString.split(",").map(_.toByte)
      val wrapperMessage = WrapperMessage.parseFrom(dataBytes)
      
      val loginResponse = LoginResponse()

      wrapperMessage.msg match {
        case Msg.Login(l) =>
          userDAO.find(l.email).map(_ match {
            case Some(user) =>
              if (BCrypt.checkpw(l.password, user.password)) {
                sender ! Tcp.Write(ByteString(loginResponse
                  .withUserId(UUID(user.id.toString))
                  .withSuccess("Login successful.").toByteArray))
              }
              else sender ! Tcp.Write(ByteString(loginResponse
                .withError("Invalid password for that user.").toByteArray))
            case None => sender ! Tcp.Write(ByteString(loginResponse
                .withError("No user exists with that email address.").toByteArray))
          })
        case Msg.Register(r) =>
          val user = new User(
            email = r.email, 
            password = r.password,
            firstName = r.firstName,
            lastName = r.lastName)

          userDAO.create(user).map(_ match {
            case Some(user) => sender ! Tcp.Write(ByteString(loginResponse
                  //.withUserId(user.id)
                  .withSuccess("Registration successful.").toByteArray))
            case None => sender ! Tcp.Write(ByteString(loginResponse
                .withError("Failed to register.").toByteArray))
          })
        case Msg.Empty => 
          // TODO
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
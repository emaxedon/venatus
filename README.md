# Venatus

## About
Venatus is a MORPG game server written in [Scala](http://scala-lang.org). This codebase is open-source and contributions made by volunteers are encouraged.
 
## Game Mechanics
The game mechanics will follow that of a basic fantasy multiplayer online RPG with a few major innovative differences. Only the basic features of common MORPGs will be implemented. These features include but are not limited to:
- User registration and authentication
- Character creation (picking class, gender, name)
- Chat system (global chat, local chat, private messages)
- NPCs
- Quest system
- Player inventory and equipment
- Player movement, spawning, class skill actions
- Player to player trading
- Player friend system
- Player vs. Player (PvP)
- Guild system
 
## Dependencies
The game server will be designed to be non-blocking, scalable, and resilient. To accomplish these goals, the following tools will be implemented:
- [Akka](http://akka.io/), a toolkit for developing asynchronous, non-blocking message-driven applications that run on the JVM
- [Google's Protocol Buffers](https://github.com/google/protobuf), a language-neutral, platform-neutral mechanism for serializing structured data
- [Slick](http://slick.typesafe.com/), a functional relational mapper that allows for type safe database queries written in Scala
- [PostgreSQL](http://www.postgresql.org/), open-source relational database management system
- [jBCrypt](http://www.mindrot.org/projects/jBCrypt/), Java implementation of OpenBSD's Blowfish password hashing
- More to come...
 
## Game Client
The game client will be a low graphics application using the Unity3D game engine. The client will be developed concurrently for testing purposes only.

## Usage
To run the game server you will need to install Scala v2.11.7+ and SBT v0.13.9+. Then, execute `sbt run` in the root directory of the project.

## License
Vinctus Venatus is distributed under the MIT License, meaning that you are free to use it in your free or proprietary software.

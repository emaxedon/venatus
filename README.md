# Venatus

## About
Venatus is a MORPG game server written in [Scala](http://scala-lang.org)). This codebase is open-source and contributions made by volunteers are welcome.
 
## Game Mechanics
The game mechanics will follow that of a basic fantasy multiplayer online RPG. The game server will not handle logic for any new or innovative forms of gameplay. Only the basic features of common MORPGs will be implemented. These features include but are not limited to:
- User registration and authentication
- Character creation (picking class, gender, name)
- Chat system (global chat, map chat, pm)
- NPCs
- Quest system
- Player level and attributes (players level up by gaining experience completing quests, and by killing non-player entities)
- Player inventory and equipment
- Player movement, spawning, class skill actions
- Player to player trading
- Player friend system
- Guild system
 
## Dependencies
The game server will be designed to be non-blocking, scalable, and resilient. To accomplish the goal of the game server, the following tools will be used:
- Akka - a toolkit for developing asynchronous, non-blocking message-driven applications that run on the JVM
- Google's Protocol Buffers - a language-neutral, platform-neutral mechanism for serializing structured data
- Slick - a functional relational mapper that allows for type safe database queries written in Scala
- PostgreSQL - open-source relational database management system
- jBCrypt - Java implementation of OpenBSD's Blowfish password hashing
- More to come...
 
## Game Client
The game client will be a low graphics application using the Unity3D game engine. The client will be developed concurrently for testing purposes only.

## Usage
To run the game server you will need to install Scala v2.11.7+ and SBT v0.13.9+. Then, execute `sbt run` in the root directory of the project.

## License
Vinctus Venatus is distributed under the MIT License, meaning that you are free to use it in your free or proprietary software.

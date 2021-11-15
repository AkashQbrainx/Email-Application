package com.qbrainx.util

import akka.actor.ActorSystem

trait App extends scala.App {
  implicit val actorSystem: ActorSystem =
    ActorSystem("myActor")


}

package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

case class Jail() extends Space {
  override def action(t_player: Player): Unit = {}
}

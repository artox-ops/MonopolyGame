package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player

import scala.collection.mutable.ArrayBuffer

case class Tax() extends Space {
  override protected val availablePlayers: ArrayBuffer[Player] = new ArrayBuffer[Player]()

  override def action(t_player: Player): Unit = print()
}
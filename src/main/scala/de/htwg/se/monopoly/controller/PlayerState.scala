package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.{Dice, Player}

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): PlayerState

  def rollDice(dice: Dice): String
}

object FreePlayerState extends PlayerState {
  override def determinePlayerState(currentPlayer: Player): PlayerState = {
    if (currentPlayer.isJailed) {
      JailedPlayerState
    } else {
      FreePlayerState
    }
  }

  override def rollDice(dice: Dice): String = {
    if (GameState.controller.getCurrentDice.hasDoublets) {
      if (dice.getFaceValue + Game.board.players(GameState.currentPlayer).getLocation == Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
        return dice.toString + "You landed on Go: here are 400$\n"
      }
      if (dice.getFaceValue + Game.board.players(GameState.currentPlayer).getLocation > Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
        return dice.toString + "You went over Go: here are 200$\n"
      }
      Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
      dice.toString
    } else {
      if (dice.getFaceValue + Game.board.players(GameState.currentPlayer).getLocation == Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
        return dice.toString + "You went over Go: here are 400$\n"
      }
      if (dice.getFaceValue + Game.board.players(GameState.currentPlayer).getLocation > Game.board.totalNumberOfSpaces) {
        Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
        return dice.toString + "You went over Go: here are 200$\n"
      }
      Game.board.movePlayer(dice.getFaceValue, GameState.currentPlayer)
      dice.toString
    }
  }
}

object JailedPlayerState extends PlayerState {

  override def determinePlayerState(currentPlayer: Player): PlayerState = {
    if (currentPlayer.isJailed) {
      JailedPlayerState
    } else {
      FreePlayerState
    }
  }

  override def rollDice(dice: Dice): String = {
    if (dice.hasDoublets) {
      Game.board.setPlayerJailedOrUnJailed(GameState.currentPlayer, jailed = false)
      val freePlayerState: PlayerState = determinePlayerState(Game.board.players(GameState.currentPlayer))
      return "You got doublets, you are now a free man!\n" + freePlayerState.rollDice(dice)
    }
    "You rolled %d and %d. ".format(dice.die1, dice.die2)  + "You are still jailed!\n"
  }
}
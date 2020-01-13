package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.Dice

import scala.swing.Publisher

class Controller extends Publisher {

  val gameState = new GameState(this)

  val mainMenu = "option | description\n [1]   | Start new game\n [200]   | Exit game"

  val gameMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val jailMenu = "option | description\n [1]   | roll dice\n [2]   | Exit game"

  val wrongCommand = "Command Option does not exist"

  def stringGameBoard(): String = {
    Game.board.toString
  }

  var currentDice: Dice = _

  def getCurrentDice: Dice = currentDice

  def rollDice(): Unit = {
    currentDice = Dice()
    publish(new RolledDice)
    gameState.nextState()
  }

  var playerState: PlayerState = FreePlayerState

  def stringRolledDice: String = {
    val message = playerState.stringRollDice(currentDice)
    playerState = playerState.rollDice(currentDice)
    message
  }

  def exitCurrentGame(): String = {
    gameState.setState("MAIN_MENU")
    publish(new ExitCurrentGame)
    exitCurrentGameMessage
  }

  val exitCurrentGameMessage: String = "Returns to main menu!"

  val exitProgramMessage: String = "Exit game!"

  def nextPlayersRoundMessage(): String = {
    val playerString = Game.board.players(gameState.currentPlayer).toString
    "It´s " + playerString + "´s turn!\n"
  }

  val rolledDoubletsMessage: String = "You rolled doublets! Roll a second time"

  def initializeGame(): Unit = {

    //change if selectable how much players are playing
    gameState.startGame(Game.numberOfPlayers)
  }
}

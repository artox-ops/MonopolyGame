package de.htwg.se.monopoly.controller

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.Player

class ControllerSpec extends WordSpec with Matchers{
  "A Controller" when {
    "new" should {
      val controller = new Controller()
      "have a String which contains the main menu options" in {
        controller.mainMenu should be("option | description\n [1]   | Start new game\n [200]   | Exit game")
      }
      "have a String which contains the in game menu options" in {
        controller.gameMenu should be("option | description\n [1]   | roll dice\n [2]   | Exit game")
      }
      "have a String which contains the jail menu options" in {
        controller.jailMenu should be("option | description\n [1]   | roll dice\n [2]   | Exit game")
      }
      "have a String that represents the answer when u use a wrong command" in {
        controller.wrongCommand should be ("Command Option does not exist")
      }
      "have a Method that returns the Gameboard as String" in {
        Game.init()
        controller.stringGameBoard() should be (Game.board.toString())
      }
      "have a method that exits the current game" in {
        GameState.setState("ROLL_DICE")
        controller.exitCurrentGame() should be (controller.exitCurrentGameMessage)
        GameState.state should be (GameState.MAIN_MENU)
      }
      "have a String that contains a message when u exit a Game" in {
        controller.exitCurrentGameMessage should be ("Returns to main menu!")
      }
      "have a String that contains a message when u exit the program" in {
        controller.exitProgramMessage should be ("Exit game!")
      }
      "have a method that returns a String which tells us the next player" in {
        GameState.setCurrentPlayer(0)
        controller.nextPlayersRoundMessage() should be ("It´s Player 1´s turn!\n")
      }
      "have a method that initializes the game" in {
        GameState.setState("MAIN_MENU")
        controller.initializeGame()
        GameState.state should be (GameState.ROLL_DICE)
      }
    }
  }
}

# Blackjack Console Game

## Overview
This is my console-based Blackjack game implemented in Java. The game allows a player to play rounds of Blackjack against a dealer. The game includes features from basic Blackjack gameplay to handling Ace values dynamically, allowing splits, double downs, and providing an enhanced betting system.

## Project Structure
- **Card.java**: Defines the properties of a card.
- **Deck.java**: Manages a deck of cards, including shuffling and dealing.
- **Game.java**: Contains the main logic for playing the game, handling player and dealer turns, determining the winner, and updating the player's balance.
- **Player.java**: Manages a player's hand and calculates the hand's value, considering Aces dynamically.
- **Suit.java**: Enum for the suits in a deck of cards.
- **Value.java**: Enum for the values in a deck of cards.

## Requirements Completion

### Level 1
1. **Create a playing deck of cards that can be shuffled**: Implemented with the `Deck` class.
2. **Deal a hand to the player**: Implemented in the `Game` class.
3. **Play a game of Blackjack**: Implemented in the `Game` class.
4. **Determine the winner**: Implemented in the `Game` class using the `determineWinner()` method.
5. **Calculate the player's new balance**: Implemented in the `Game` class, balance updates after each round.

### Level 2
1. **Betting in increments of 5**: Implemented with a default bet of $10 and allowing dynamic betting in increments of 5.
2. **Double down**: Implemented the functionality for doubling down.

### Level 3
1. **Split cards**: Implemented the functionality for splitting cards and playing two hands in unison if the initial two cards are of the same value.

## Extra Functionality
1. **Improved Console Readability**: Enhanced the console output with separators and formatted messages for better readability.
2. **Dynamic Handling of Ace Values**: Aces are counted as 1 or 11 dynamically to prevent busts.
3. **Welcome Message and Starting Balance Display**: Added a welcome message and displayed the player's starting balance at the start of the game.
4. **Dynamic Betting**: Allowed players to choose their bet amount at the beginning of each round.

## How to Play
1. Run the `Main` class to start the game.
2. Follow the prompts in the console to play rounds of Blackjack.
3. After each round, the game will display the results and update your balance.
4. You can choose to play another round or exit the game.

## Running the Game
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Ensure you have JDK 11 installed.
4. Run the `Main` class to start playing.

Enjoy playing my Blackjack!

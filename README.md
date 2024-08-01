# Blackjack Console Game

## Overview
This is a console-based Blackjack game implemented in Java. The game allows a player to play rounds of Blackjack against a dealer. The game includes features from basic Blackjack gameplay to handling Ace values dynamically and improving console readability.

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
1. **Betting in increments of 5**: Implemented with a default bet of $10.
2. **Double down**: This functionality is not yet implemented.

### Level 3
1. **Split cards**: Split functionality is acknowledged but not fully implemented.

## Extra Functionality
1. **Improved Console Readability**: Enhanced the console output with separators and formatted messages for better readability.
2. **Dynamic Handling of Ace Values**: Aces are counted as 1 or 11 dynamically to prevent busts.
3. **Welcome Message and Starting Balance Display**: Added a welcome message and displayed the player's starting balance at the start of the game.

## How to Play
1. Run the `Main` class to start the game.
2. Follow the prompts in the console to play rounds of Blackjack.
3. After each round, the game will display the results and update your balance.
4. You can choose to play another round or exit the game.

## Future Enhancements
1. **Double Down**: Implement the functionality for doubling down.
2. **Split Cards**: Complete the implementation for splitting cards.
3. **Enhanced Betting System**: Allow variable betting amounts in increments of 5.

## Running the Game
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Ensure you have JDK 11 installed.
4. Run the `Main` class to start playing.

Enjoy playing my Blackjack!

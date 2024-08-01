package org.example;

import java.util.Scanner;

public class Game {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private int playerBalance;
    private int currentBet;
    private static final int DEFAULT_BET = 10;

    public Game() {
        deck = new Deck();
        player = new Player("Player");
        dealer = new Player("Dealer");
        playerBalance = 100; // Starting balance
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        // Welcome message
        System.out.println("==================================");
        System.out.println("   Welcome to Blackjack!   ");
        System.out.println("==================================");
        System.out.println("Your starting balance: $" + playerBalance);
        System.out.println();

        while (playing) {
            System.out.println("==================================");
            System.out.println("Starting a new round...");
            System.out.println("==================================");
            System.out.println("Enter your bet (increments of 5): ");
            currentBet = scanner.nextInt();
            while (currentBet % 5 != 0 || currentBet > playerBalance) {
                System.out.println("Invalid bet amount. Please enter a bet in increments of 5 and not more than your balance:");
                currentBet = scanner.nextInt();
            }

            deck.shuffle();
            player.clearHand();
            dealer.clearHand();

            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's visible card: " + dealer.getHand().get(0));
            System.out.println("==================================");

            // Check for Blackjack
            if (player.getHandValue() == 21 && player.getHand().size() == 2) {
                System.out.println("Blackjack! You win!");
                playerBalance += currentBet * 1.5; // Blackjack pays 1.5 times the bet
                System.out.println("Your balance: $" + playerBalance);
                System.out.println("==================================");
                System.out.println("Do you want to play another round? (y/n)");
                playing = scanner.next().equalsIgnoreCase("y");
                continue;
            }

            // Check for split option
            if (player.getHand().get(0).getValue() == player.getHand().get(1).getValue()) {
                System.out.println("Do you want to split your hand? (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    // For simplicity, I will not implement the split logic fully here
                    System.out.println("Splitting is not yet implemented.");
                }
            }

            // Player's turn
            boolean playerTurn = true;
            boolean doubleDown = false;
            while (playerTurn) {
                System.out.println("Choose an action: (1) Hit, (2) Stand, (3) Double Down");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust!");
                        playerBalance -= currentBet;
                        playerTurn = false;
                        break;
                    } else if (player.getHandValue() == 21) {
                        System.out.println("You have 21!");
                        playerTurn = false;
                    }
                } else if (choice == 3 && !doubleDown) {
                    doubleDown = true;
                    currentBet *= 2;
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust!");
                        playerBalance -= currentBet;
                    }
                    playerTurn = false;
                } else {
                    playerTurn = false;
                }
            }

            if (player.getHandValue() <= 21) {
                // Dealer's turn
                while (dealer.getHandValue() < 17) {
                    dealer.addCard(deck.dealCard());
                }
                System.out.println("Dealer's hand: " + dealer.getHand());
                System.out.println("==================================");

                // Determine the winner
                determineWinner();
            }

            // Update player balance
            System.out.println("Your balance: $" + playerBalance);
            System.out.println("==================================");

            // Check if player wants to continue
            System.out.println("Do you want to play another round? (y/n)");
            playing = scanner.next().equalsIgnoreCase("y");
        }
        scanner.close();
    }

    private void determineWinner() {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        if (playerValue > 21) {
            System.out.println("You lose!");
            playerBalance -= currentBet;
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("You win!");
            playerBalance += currentBet;
        } else if (playerValue == dealerValue) {
            System.out.println("It's a tie!");
            // In a tie, the player's balance remains unchanged
        } else {
            System.out.println("You lose!");
            playerBalance -= currentBet;
        }
    }
}

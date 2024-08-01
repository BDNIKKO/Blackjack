package org.example;

import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    private int playerBalance;
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

        while (playing) {
            System.out.println("Starting a new round...");
            deck.shuffle();
            player.clearHand();
            dealer.clearHand();

            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's visible card: " + dealer.getHand().get(0));

            // Player's turn
            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Choose an action: (1) Hit, (2) Stand");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust!");
                        playerTurn = false;
                    }
                } else {
                    playerTurn = false;
                }
            }

            // Dealer's turn
            while (dealer.getHandValue() < 17) {
                dealer.addCard(deck.dealCard());
            }
            System.out.println("Dealer's hand: " + dealer.getHand());

            // Determine the winner
            determineWinner();

            // Update player balance
            System.out.println("Your balance: " + playerBalance);

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
            playerBalance -= DEFAULT_BET;
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("You win!");
            playerBalance += DEFAULT_BET;
        } else if (playerValue == dealerValue) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("You lose!");
            playerBalance -= DEFAULT_BET;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}

package org.example;

import java.util.Scanner;

public class Game {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private Player splitPlayer;
    private int playerBalance;
    private int currentBet;

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
                playerBalance += (int) (currentBet * 1.5); // Blackjack pays 1.5 times the bet
                System.out.println("Your balance: $" + playerBalance);
                System.out.println("==================================");
                System.out.println("Do you want to play another round? (y/n)");
                playing = scanner.next().equalsIgnoreCase("y");
                continue;
            }

            // Check for split option
            boolean split = false;
            if (player.getHand().get(0).getValue() == player.getHand().get(1).getValue()) {
                System.out.println("Do you want to split your hand? (y/n)");
                if (scanner.next().equalsIgnoreCase("y")) {
                    split = true;
                    splitPlayer = new Player("Split Hand");
                    splitPlayer.addCard(player.getHand().remove(1));
                    player.addCard(deck.dealCard());
                    splitPlayer.addCard(deck.dealCard());

                    System.out.println("Your hand: " + player.getHand());
                    System.out.println("Split hand: " + splitPlayer.getHand());
                }
            }

            // Player's turn
            while (true) {
                System.out.println("Choose an action: (1) Hit, (2) Stand, (3) Double Down");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust!");
                        playerBalance -= currentBet;
                        break;
                    } else if (player.getHandValue() == 21) {
                        System.out.println("You have 21!");
                        break;
                    }
                } else if (choice == 3) {
                    currentBet *= 2;
                    player.addCard(deck.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    if (player.getHandValue() > 21) {
                        System.out.println("You bust!");
                        playerBalance -= currentBet;
                    }
                    break;
                } else {
                    break;
                }
            }

            if (split) {
                // Split Player's turn
                while (true) {
                    System.out.println("Split hand - Choose an action: (1) Hit, (2) Stand, (3) Double Down");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        splitPlayer.addCard(deck.dealCard());
                        System.out.println("Split hand: " + splitPlayer.getHand());
                        if (splitPlayer.getHandValue() > 21) {
                            System.out.println("Split hand busts!");
                            playerBalance -= currentBet;
                            break;
                        } else if (splitPlayer.getHandValue() == 21) {
                            System.out.println("Split hand has 21!");
                            break;
                        }
                    } else if (choice == 3) {
                        currentBet *= 2;
                        splitPlayer.addCard(deck.dealCard());
                        System.out.println("Split hand: " + splitPlayer.getHand());
                        if (splitPlayer.getHandValue() > 21) {
                            System.out.println("Split hand busts!");
                            playerBalance -= currentBet;
                        }
                        break;
                    } else {
                        break;
                    }
                }
            }

            if (player.getHandValue() <= 21) {
                // Dealer's turn
                while (dealer.getHandValue() < 17) {
                    dealer.addCard(deck.dealCard());
                }
                System.out.println("Dealer's hand: " + dealer.getHand());
                System.out.println("==================================");

                // Determine the winner for the main hand
                determineWinner(player, "Your hand");

                if (split) {
                    // Determine the winner for the split hand
                    determineWinner(splitPlayer, "Split hand");
                }
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

    private void determineWinner(Player handPlayer, String handName) {
        int playerValue = handPlayer.getHandValue();
        int dealerValue = dealer.getHandValue();

        System.out.println(handName + ": " + handPlayer.getHand());

        if (playerValue > 21) {
            System.out.println("You lose with " + handName + "!");
            playerBalance -= currentBet;
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("You win with " + handName + "!");
            playerBalance += currentBet;
        } else if (playerValue == dealerValue) {
            System.out.println("It's a tie with " + handName + "!");
            // In a tie, the player's balance remains unchanged
        } else {
            System.out.println("You lose with " + handName + "!");
            playerBalance -= currentBet;
        }
    }
}
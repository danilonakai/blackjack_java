# Blackjack Game in Java

This is a simple Blackjack game implementation using Java and JavaFX, built for a college project. The game follows the rules of Blackjack where the player competes against a dealer to get as close as possible to a hand value of 21 without going over. The game includes features like card drawing, calculating total points, handling Aces as 1 or 11, and determining the winner based on the final hand values.

## Features
- **Player and Dealer Simulation**: The player competes against the dealer, both of whom draw cards and make decisions based on hand values.
- **Card Handling**: Cards are randomly drawn from a deck, with special handling for Aces (counted as either 1 or 11).
- **Graphical User Interface (GUI)**: The game is built using JavaFX, providing an interactive visual experience for the player.
- **Game Flow**: The player has options to "Hit" (draw a new card) or "Stand" (keep their current hand). The dealer draws cards based on standard Blackjack rules.
- **Loss Condition**: If the player's or dealer's hand exceeds 21, they lose the round.

## Classes
- **Main.java**: The entry point for the game, which initializes the JavaFX window and starts the game by asking for the player's name and setting up the `Blackjack` game object.
- **Dealer.java**: This class handles the dealer's logic, including card drawing, determining the dealer's total, and deciding when to "hit" or "stand."
- **Blackjack.java**: The core of the game, controlling the flow of the game, determining the player's and dealer's actions, and calculating the winner.
- **Player.java**: This class represents the player, managing their hand of cards, calculating their total points, and determining when they lose based on the rules.


## Technologies Used
- **Java**: The primary programming language used for this project.
- **JavaFX**: Used for creating the graphical user interface (GUI).

## Getting Started
1. Clone the repository to your local machine.
2. Open the project in **IntelliJ IDEA** (Community Edition).
3. Make sure to use **Java Version 15** (Liberica JDK 15) or higher.
3. Compile and run `Main.java` using any IDE or the command line.

## Sample Output
![image](https://github.com/user-attachments/assets/fb75dc3a-72b6-41c1-9156-5ae1cff5cbc2)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

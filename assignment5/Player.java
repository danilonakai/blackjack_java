package assignment5;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Orientation;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * BlackJack player controlled by the player
 *
 * @author Danilo Nakai
 */
public class Player {
    /**
     * Player's name
     **/
    private String name;

    /**
     * Player's card list
     **/
    private ArrayList cardList;

    /**
     * Player's points
     **/
    private int points = 10;

    /**
     * Constructor method of the class
     *
     * @param name Player's name
     **/
    public Player(String name) {
        this.name = name;

        newCardList();
    }

    /**
     * Set the player's points according to value passed as parameter
     *
     * @param points Amount of points to set
     **/
    public void setPoints(int points) {
        this.points = this.points + points;
    }

    /**
     * Get player's name
     *
     * @return player's name
     **/
    public String getName() {
        return name;
    }

    /**
     * Get player's points
     *
     * @return player's points
     **/
    public int getPoints() {
        return points;
    }

    /**
     * Calculate player's total sum of cards
     *
     * @return player's total sum of cards
     **/
    public int getTotal() {
        int totalAis11 = 0;
        int totalAis1 = 0;

        for (int i = 0; i < cardList.size(); i++) {
            if ((int) cardList.get(i) > 10) {
                totalAis11 = totalAis11 + 10;
                totalAis1 = totalAis1 + 10;
            } else if ((int) cardList.get(i) == 1) {
                totalAis11 = totalAis11 + 11;
                totalAis1 = totalAis1 + 1;
            } else {
                totalAis11 = totalAis11 + (int) cardList.get(i);
                totalAis1 = totalAis1 + (int) cardList.get(i);
            }
        }

        if (totalAis11 <= 21) {
            return totalAis11;
        } else {
            return totalAis1;
        }
    }

    /**
     * Add a random card to player's card list
     *
     * @param root object
     **/
    public void addCardTocardList(Pane root) {
        Random random = new Random();
        cardList.add(random.nextInt((13 + 1) - 1) + 1);

        draw(root);
    }

    /**
     * Convert the card name
     *
     * @param card number of the card
     * @return the new format
     **/
    public String convertCard(int card) {
        String convertedCard = null;

        if (card >= 2 && card <= 10) {
            convertedCard = Integer.toString(card);
        } else if (card == 1) {
            convertedCard = "A";
        } else if (card == 11) {
            convertedCard = "J";
        } else if (card == 12) {
            convertedCard = "Q";
        } else if (card == 13) {
            convertedCard = "K";
        }

        return convertedCard;
    }

    /**
     * Create a new card list
     **/
    public void newCardList() {
        cardList = new ArrayList();

        for (int i = 0; i < 2; i++) {
            Random random = new Random();
            cardList.add(random.nextInt((13 + 1) - 1) + 1);
        }
    }

    /**
     * Check if the player lost
     *
     * @return the boolean value true of false
     **/
    public boolean hasLost() {
        boolean lost = false;

        if (getTotal() > 21) {
            lost = true;
        }

        return lost;
    }

    /**
     * Draw the player's card list
     *
     * @param root object
     **/
    public void draw(Pane root) {
        Label title = new Label(name + " - " + points + " Points");
        title.setFont(new Font("Calibri", 20));
        title.setTextFill(Color.WHITE);
        title.relocate(100, 450);

        Rectangle rect = new Rectangle(100, 480, 600, 100);
        rect.setFill(Color.web("#00592a"));

        TilePane cards = new TilePane(Orientation.HORIZONTAL);
        cards.setStyle("-fx-min-width:580px;");
        cards.relocate(110, 490);

        for (int i = 0; i < cardList.size(); i++) {
            Label test = new Label(convertCard((int) cardList.get(i)));
            test.setStyle("-fx-padding:5px 30px 60px 5px;-fx-min-width:50px");
            test.setBackground(Background.fill(Color.WHITE));
            StackPane t = new StackPane();
            t.setStyle("-fx-padding:0 10px;");

            t.getChildren().add(test);

            cards.getChildren().add(t);
        }

        root.getChildren().addAll(rect, title, cards);
    }
}

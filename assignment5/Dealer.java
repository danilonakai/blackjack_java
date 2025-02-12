package assignment5;

import javafx.geometry.Orientation;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * BlackJack dealer controlled by the system
 *
 * @author Danilo Nakai
 */
public class Dealer {
    /**
     * Dealer's card list
     **/
    private ArrayList cardList;

    /**
     * Dealer's points
     **/
    private int points = 10;

    /**
     * Constructor method of the class
     **/
    public Dealer() {
        newCardList();
    }

    /**
     * Set the dealer's points according to value passed as parameter
     *
     * @param points Amount of points to set
     **/
    public void setPoints(int points) {
        this.points = this.points + points;
    }

    /**
     * Get dealer's points
     *
     * @return dealer's points
     **/
    public int getPoints() {
        return points;
    }

    /**
     * Calculate dealer's total sum of cards
     *
     * @return dealer's total sum of cards
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
     * Add a random card to dealer's card list
     *
     * @param root object
     **/
    public void addCardTocardList(Pane root) {
        Random random = new Random();
        cardList.add(random.nextInt((13 + 1) - 1) + 1);

        draw(root, false);
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
     * Check if the dealer lost
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
     * Check if the dealer need one more card
     *
     * @return boolean value of true or false
     **/
    public boolean needMore() {
        boolean needMore = false;

        if (getTotal() < 17) {
            needMore = true;
        }

        return needMore;
    }

    /**
     * Draw the dealer's card list
     *
     * @param root object
     * @param hideCard boolean value if it is to hide the card
     **/
    public void draw(Pane root, boolean hideCard) {
        Label title = new Label("Dealer - " + points + " Points");
        title.setFont(new Font("Calibri", 20));
        title.setTextFill(Color.WHITE);
        title.relocate(100, 50);

        Rectangle rect = new Rectangle(100, 80, 600, 100);
        rect.setFill(Color.web("#00592a"));

        TilePane cards = new TilePane(Orientation.HORIZONTAL);
        cards.setStyle("-fx-min-width:580px;");
        cards.relocate(110, 90);

        for (int i = 0; i < cardList.size(); i++) {
            Label test = new Label(convertCard((int) cardList.get(i)));
            test.setTextFill(Color.BLACK);
            test.setStyle("-fx-padding:5px 30px 60px 5px;-fx-min-width:50px");
            test.setBackground(Background.fill(Color.WHITE));
            if (hideCard) {
                if (i == 1) {
                    test.setBackground(Background.fill(Color.BLACK));
                }
            }

            StackPane t = new StackPane();
            t.setStyle("-fx-padding:0 10px;");

            t.getChildren().add(test);

            cards.getChildren().add(t);
        }

        root.getChildren().addAll(rect, title, cards);
    }
}

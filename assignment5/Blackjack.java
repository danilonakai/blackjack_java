package assignment5;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.awt.*;
import java.util.ArrayList;

/**
 * BlackJack class that control the game
 * @author Danilo Nakai
 */
public class Blackjack {
    /** Player's object **/
    private Player player;

    /** Dealer's object **/
    private Dealer dealer;

    /**
     * Constructor method of the class
     * @param playerName player's name
     * @param root root object
     **/
    public Blackjack(String playerName, Pane root){
        this.player = new Player(playerName);
        this.dealer = new Dealer();

        draw(root);
        player.draw(root);
        dealer.draw(root,true);
    }

    /**
     * Draw the BlackJack's action buttons
     *
     * @param root root object
     **/
    public void draw(Pane root){
        drawEssentials(root);

        // STAND BUTTON
        Button stand = new Button("Stand");
        stand.setTextFill(Color.WHITE);
        stand.setFont(new Font("Calibri",15));
        stand.setStyle("-fx-background-color:#00592a;-fx-padding:10px 15px;");

        // HIT BUTTON
        Button hit = new Button("Hit");
        hit.setTextFill(Color.WHITE);
        hit.setFont(new Font("Calibri",15));
        hit.setStyle("-fx-background-color:#00592a;-fx-padding:10px 15px;");

        // STAND BUTTON CLICK EVENT
        stand.setOnAction(event -> standClicked(root));

        // HIT BUTTON CLICK EVENT
        hit.setOnAction(event -> hitClicked(root));

        // BUTTON ALIGNMENT GROUP
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.relocate(100,600);
        tileButtons.getChildren().addAll(stand,hit);

        // ADDING ALL ELEMENTS
        root.getChildren().add(tileButtons);
    }

    /**
     * Draw the BlackJack's board and essential elements
     *
     * @param root root object
     **/
    public void drawEssentials(Pane root){
        // BOARD BACKGROUND
        Rectangle board = new Rectangle(0,0,800,700);
        board.setFill(Color.web("#007d3b"));

        // CREDITS
        javafx.scene.control.Label credits = new Label("Created by Danilo Nakai");
        credits.setFont(new Font("Calibri",15));
        credits.setTextFill(Color.WHITE);
        credits.relocate(5,5);

        // ADDING ALL ELEMENTS
        root.getChildren().addAll(board,credits);
    }

    /**
     * Draw the BlackJack's end screen
     *
     * @param root root object
     **/
    public void drawEndGame(Pane root){
        // MESSAGE
        javafx.scene.control.Label message = new Label();
        message.setFont(new Font("Calibri",15));
        message.setTextFill(Color.WHITE);
        message.relocate(300,280);

        if(player.getPoints() == 0){
            message.setText("Game Over! \n Dealer is the greatest winner.");
        }else if(dealer.getPoints() == 0){
            message.setText("Game Over! \n "+player.getName()+" is the greatest winner.");
        }

        root.getChildren().add(message);
    }

    /**
     * Action performed when the stand button is clicked
     *
     * @param root root object
     **/
    public void standClicked(Pane root){
        if(!player.hasLost()){
            dealer.draw(root,false);

            while(dealer.needMore()){
                dealer.addCardTocardList(root);
            }

            if(dealer.hasLost()){
                // MESSAGE
                javafx.scene.control.Label message = new Label("Dealer lost!");
                message.setFont(new Font("Calibri",15));
                message.setTextFill(Color.WHITE);
                message.relocate(350,280);

                // CONTINUE BUTTON
                Button resetGame = new Button("Continue");
                resetGame.setTextFill(Color.WHITE);
                resetGame.setFont(new Font("Calibri",15));
                resetGame.setStyle("-fx-background-color:#00592a;-fx-padding:10px 15px;");
                resetGame.relocate(350,310);

                resetGame.setOnAction(event -> resetGame(root,"player"));

                root.getChildren().addAll(message,resetGame);
            }else{
                // MESSAGE
                javafx.scene.control.Label message = new Label();
                message.setFont(new Font("Calibri",15));
                message.setTextFill(Color.WHITE);
                message.relocate(350,280);


                // CONTINUE BUTTON
                Button resetGame = new Button("Continue");
                resetGame.setTextFill(Color.WHITE);
                resetGame.setFont(new Font("Calibri",15));
                resetGame.setStyle("-fx-background-color:#00592a;-fx-padding:10px 15px;");
                resetGame.relocate(350,310);

                if(dealer.getTotal() > player.getTotal()){
                    message.setText("Dealer won!");
                    resetGame.setOnAction(event -> resetGame(root,"dealer"));
                }else if(dealer.getTotal() < player.getTotal()){
                    message.setText(player.getName()+" won!");
                    resetGame.setOnAction(event -> resetGame(root,"player"));
                }else{
                    message.setText("It's a draw!");
                    resetGame.setOnAction(event -> resetGame(root,"draw"));
                }

                root.getChildren().addAll(message,resetGame);
            }
        }
    }

    /**
     * Action performed when the hit button is clicked
     *
     * @param root root object
     **/
    public void hitClicked(Pane root){
        if(!player.hasLost()){
            player.addCardTocardList(root);

            if(player.hasLost()){
                // MESSAGE
                javafx.scene.control.Label message = new Label("You lost!");
                message.setFont(new Font("Calibri",15));
                message.setTextFill(Color.WHITE);
                message.relocate(350,280);

                // CONTINUE BUTTON
                Button resetGame = new Button("Continue");
                resetGame.setTextFill(Color.WHITE);
                resetGame.setFont(new Font("Calibri",15));
                resetGame.setStyle("-fx-background-color:#00592a;-fx-padding:10px 15px;");
                resetGame.relocate(350,310);

                resetGame.setOnAction(event -> resetGame(root,"dealer"));

                root.getChildren().addAll(message,resetGame);
            }
        }
    }
    
    /**
     * Give 1 point to the winner and remove from the loser
     * start a new round
     *
     * @param root root object
     * @param winner current round's winner
     **/
    public void resetGame(Pane root,String winner){
        root.getChildren().clear();

        if(winner == "dealer"){
            player.setPoints(-1);
            dealer.setPoints(1);
        }else if(winner == "player"){
            player.setPoints(1);
            dealer.setPoints(-1);
        }

        if(player.getPoints() == 0 || dealer.getPoints() == 0){
            drawEssentials(root);

            drawEndGame(root);
        }else{
            player.newCardList();
            dealer.newCardList();

            draw(root);
            player.draw(root);
            dealer.draw(root,true);
        }
    }
}

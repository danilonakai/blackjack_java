package assignment5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;
import java.awt.*;
import java.util.Scanner;

/**
 * Main class that use the BlackJack model class to play the game
 * @author Danilo Nakai
 */
public class Main extends Application {

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 700); // set the size here
        stage.setTitle("BlackJack"); // set the window title here
        stage.setScene(scene);

        Scanner input = new Scanner(System.in);

        System.out.println("What's your name?"); // Ask player to type the name
        String userName = input.nextLine();

        Blackjack game = new Blackjack(userName,root); // Start a new game

        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}

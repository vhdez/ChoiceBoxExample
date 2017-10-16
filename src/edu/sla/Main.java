package edu.sla;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // static variables ONLY needed by RickRoll_click method
    //                   NOT needed by ChoiceBox's changed method
    static Image     imageAhead;
    static ImageView imageView;
    static ChoiceBox cb;

    public static void RickRoll_click() {
        // button clicked, so show Rick looking ahead
        imageView.setImage(imageAhead);
        // turn on the choicebox
        if (cb.isDisabled()) {
            cb.setDisable(false);
        }
        // Make sure my choicebox's selection matches with image
        cb.setValue("Ahead");
    }

    //@Override
    public void start(Stage primaryStage) throws Exception {
        // Load all 5 images
        imageAhead = new Image("RickRolly.png" , 800, 500,false, true);
        Image imageLeft = new Image("RickLeft.jpg", 800, 500, false, true);
        Image imageRight = new Image("RickRight.png", 800 , 500, false ,true);
        Image imageDown = new Image("RickRollDown.png", 800, 500, false, true);
        Image imageUp = new Image("RickRollUp.jpg", 800, 500, false, true);
        // Create imageView where 5 images will be displayed (initially NO image is displayed)
        imageView = new ImageView();

        // Create button that initiates Rick-Rolling
        Button RickRoll = new Button("Click Here");
        // When button is clicked, display RickRoll image, enable choicebox, and make choicebox match image
        RickRoll.setOnAction(e -> RickRoll_click());

        // Create choicebox that lets user choose between 5 types of Rick-Rolling
        cb = new ChoiceBox(FXCollections.observableArrayList("Ahead", "Left", "Right", "Up", "Down"));
        // Add "changed" method that listens for choicebox selection changing
        cb.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        // when choicebox selection changes, match image to selection number
                        if (new_value.intValue() == 0) {
                            // choicebox had "Ahead" selected
                            imageView.setImage(imageAhead);
                        } else if (new_value.intValue() == 1) {
                            // choicebox had "Left" selected
                            imageView.setImage(imageLeft);
                        } else if (new_value.intValue() == 2) {
                            // choicebox had "Right" selected
                            imageView.setImage(imageRight);
                        } else if (new_value.intValue() == 3) {
                            // choicebox had "Up" selected
                            imageView.setImage(imageUp);
                        } else if (new_value.intValue() == 4) {
                            // choicebox had "Down" selected
                            imageView.setImage(imageDown);
                        }
                    }
                });
        // initially, choicebox is disabled until RickRolling button is pressed
        cb.setDisable(true);

        // Use a BorderPane as the root for scene
        // A border pane has 5 regions to put controls: Top, Bottom, Left, Right, and Center
        BorderPane border = new BorderPane();

        // Create AnchorPane for RickRoll button and choicebox
        //    Use AnchorPane instead of HBox to display choicebox on right edge of window
        AnchorPane topAnchorPane = new AnchorPane();
        topAnchorPane.getChildren().addAll(RickRoll, cb);
        // Anchor RickRoll button on left after 5.0 pixels from leftmost edge
        AnchorPane.setLeftAnchor(RickRoll, 5.0);
        // Anchor Choicebox  on right before 5.0 pixels to rightmost edge
        AnchorPane.setRightAnchor(cb, 5.0);
        // Set the Top region of the BorderPane to be this AnchorPane
        border.setTop(topAnchorPane);

        // Set the Center region of the BorderPane to be the imageView
        border.setCenter(imageView);

        // Make a Scene from the BorderPane
        Scene scene = new Scene(border, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChoiceBox Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

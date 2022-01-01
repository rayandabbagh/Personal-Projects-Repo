import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Rayan
 * @version 1
 */
public class MinesweeperView extends Application {
    /**
     * Main method.
     * @param args arguments passed in
     */
    public static void main(String[] args) {
        launch(args);
    }

    private ComboBox combo;
    private TextField nameField;

    /**
     * Start method overriden.
     * @param primaryStage Stage passed in
     */
    @Override
    public void start(Stage primaryStage) {
        VBox v = new VBox();
        Text t = new Text("Welcome to Minesweeper!");
        t.setFont(new Font("verdana", 20));
        ObservableList<String> options = FXCollections.observableArrayList(
                "Easy", "Medium", "Hard"
        );
        combo = new ComboBox(options);
        combo.setPromptText("Select difficulty....");

        nameField = new TextField();
        nameField.setPromptText("Name...");

        Button startBtn = new Button("Start");

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Warning");
        alert.setContentText("Invalid inputs, Please select a difficulty and enter your name.");

        startBtn.setOnAction(e -> {
            alert.showAndWait();
        });

        HBox pane = new HBox(15);

        pane.setPadding(new Insets(50, 150, 50, 60));
        pane.getChildren().addAll(startBtn);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();


        startBtn.setOnAction(e -> {
            if ((nameField.getText().isEmpty()) || (combo.getValue() == null)) {
                alert.show();
            } else {
                Difficulty diff;
                switch (combo.getValue().toString()) {
                case "Easy":
                    diff = Difficulty.EASY;
                    break;
                case "Medium":
                    diff = Difficulty.MEDIUM;
                    break;
                default:
                    diff = Difficulty.HARD;

                }
                playMineSweeper(primaryStage, diff, nameField.getText());
            }
        });
        StackPane rectext = new StackPane();
        Rectangle rec = new Rectangle(150, 50);
        rec.setFill(Color.WHITE);
        rec.setStroke(Color.BLACK);
        Text wishLuck = new Text("Best of luck y'all!");
        Font font3 = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15);
        wishLuck.setFont(font3);
        rectext.getChildren().addAll(rec, wishLuck);
        v.getChildren().addAll(t, combo, nameField, startBtn, rectext);
        v.setAlignment(Pos.CENTER);
        v.setSpacing(80);
        v.setPadding(new Insets(20));

        Scene mainScene = new Scene(v, 800, 800);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(mainScene);
        primaryStage.show();


    }

    /**
     * Method to play the game.
     * @param primaryStage Stage/Window
     * @param difficulty Difficulty level (enum)
     * @param name String representing the name
     */
    private void playMineSweeper(Stage primaryStage, Difficulty difficulty, String name) {
        MinesweeperGame n = new MinesweeperGame(difficulty);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(240), ev -> {
            timeOver(primaryStage, name);
        }));
        timeline.play();
        GridPane newPane = new GridPane();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Button tileButton = new Button("X");
                int finalJ = j;
                int finalI = i;
                tileButton.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Object node = event.getSource();
                        ((Button) node).setStyle("-fx-background-color: #F08080;");
                        ((Button) node).setText("F");
                    }
                });
                tileButton.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * Handle method to handle an event.
                     * @param actionEvent Event due to user input
                     */
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Tile[] tiles = n.check(finalJ, finalI);
                        //if user clicked on mine
                        if (tiles != null) {
                            if (tiles.length == 1 && tiles[0].isMine()) {
                                Object node = actionEvent.getSource();
                                ((Button) node).setStyle("-fx-background-color: #ff0000;");
                                youLost(primaryStage, name);
                            } else {
                                for (int k = 0; k < tiles.length; k++) {
                                    for (Node node : newPane.getChildren()) {
                                        if (newPane.getColumnIndex(node) == tiles[k].getY()
                                                && newPane.getRowIndex(node) == tiles[k].getX()) {
                                            ((Button) node).setText(String.valueOf(tiles[k].getBorderingMines()));
                                            ((Button) node).setStyle("-fx-background-color: #add8e6;");

                                        }
                                    }
                                }
                                if (n.isWon()) {
                                    youWon(primaryStage, name);
                                }
                            }
                        }
                    }
                });
                newPane.add(tileButton, j, i);
            }
        }
        Scene gameScene = new Scene(newPane, 450, 450);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    /**
     * You lost screen.
     * @param primaryStage Stage/Window
     * @param name String representing the name
     */
    private void youLost(Stage primaryStage, String name) {
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        Scene youLostScene = new Scene(vbox, 200, 150);
        Text youLostText = new Text("You Lost, " + name);
        youLostText.setFont(Font.font("Verdana", 20));
        youLostText.setFill(Color.RED);
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(new EndGameButtonHandler(primaryStage));
        vbox.getChildren().addAll(youLostText, newGameBtn);
        primaryStage.setScene(youLostScene);

    }

    /**
     * You won screen.
     * @param primaryStage Stage/Window
     * @param name String representing the name
     */
    private void youWon(Stage primaryStage, String name) {
        VBox vbox = new VBox();
        Scene youWonScene = new Scene(vbox, 200, 150);
        Text youLostText = new Text("Congratulations, " + name);
        youLostText.setFont(Font.font("Verdana", 20));
        youLostText.setFill(Color.GREEN);
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(new EndGameButtonHandler(primaryStage));
        vbox.getChildren().addAll(youLostText, newGameBtn);
        primaryStage.setScene(youWonScene);

    }

    /**
     * Time over method (timer).
     * @param primaryStage Stage/Window
     * @param name String representing the name
     */
    private void timeOver(Stage primaryStage, String name) {
        VBox vbox = new VBox();
        Scene youLostScene = new Scene(vbox, 200, 150);
        Text youLostText = new Text("Time is Over, " + name);
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(new EndGameButtonHandler(primaryStage));
        vbox.getChildren().addAll(youLostText, newGameBtn);
        primaryStage.setScene(youLostScene);

    }

    /**
     * @author Rayan
     * @version 1
     */
    class EndGameButtonHandler implements EventHandler<ActionEvent> {
        private final Stage primaryStage;

        /**
         * End game button Handler constructor (one args constructor).
         * @param primaryStage Stage/Window
         */
        EndGameButtonHandler(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        /**
         * Handle method to handle an event.
         * @param actionEvent represents an event due to user input
         */
        @Override
        public void handle(ActionEvent actionEvent) {
            start(primaryStage);
        }
    }
}

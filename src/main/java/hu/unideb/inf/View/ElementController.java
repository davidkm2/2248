package hu.unideb.inf.View;

import hu.unideb.inf.Dao.HighScore;
import hu.unideb.inf.Dao.HighScoreDAO;
import hu.unideb.inf.Dao.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ElementController class implements the elements of the 2048 game.
 */
public class ElementController {
    private ArrayList<Score> highScoreArrayList;
    private ObservableList<Score> lista;
    private HighScoreDAO highScoreDAO = new HighScoreDAO();

    /**The backgound {@link Rectangle}.*/
    public Rectangle rectangle = new Rectangle(85, 85, 400,400);

    /**The "New game" {@link Button}.*/
    public Button newgame = new Button("Új játék");

    /**The "High score" {@link Button}.*/
    public Button highscore = new Button("Eredmények");

    /**The "Back" {@link Button}.*/
    public Button back = new Button("Vissza");

    /**The "Score" {@link Label}.*/
    public Label scoreLabel = new Label("Score:");

    /**The score {@link Label}.*/
    public Label scoreText = new Label("0");

    /**The "Game over" {@link Label}.*/
    public Label failLabel = new Label("Játék vége!");

    /**{@link TableView} of high score .*/
    public TableView<Score> listView = new TableView<>();

    /**
     * The constructor of the ElementController.
     */
    public ElementController() {
        initElements();
        initTable();
    }

    private void initElements(){
        rectangle.setFill(Color.rgb(187,173,160));
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(30);

        newgame.setTranslateX(200);
        newgame.setTranslateY(300);
        newgame.setMaxWidth(500);
        newgame.setMaxHeight(48);
        newgame.setStyle("-fx-background-color: rgb(105,105,105);-fx-background-insets: 0,1,2,3;-fx-background-radius: 8;-fx-padding: 20 38 20 38; -fx-font-family: 'sans-serif'; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; ");
        newgame.setVisible(false);

        back.setTranslateX(220);
        back.setTranslateY(400);
        back.setMaxWidth(500);
        back.setMaxHeight(48);
        back.setStyle("-fx-background-color: rgb(105,105,105);-fx-background-insets: 0,1,2,3;-fx-background-radius: 8;-fx-padding: 20 38 20 38; -fx-font-family: 'sans-serif'; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; ");
        back.setVisible(false);

        highscore.setTranslateX(200);
        highscore.setTranslateY(400);
        highscore.setMaxWidth(400);
        highscore.setMaxHeight(48);
        highscore.setStyle("-fx-background-color: rgb(105,105,105);-fx-background-insets: 0,1,2,3;-fx-background-radius: 8;-fx-padding: 20 38 20 38; -fx-font-family: 'sans-serif'; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; ");
        highscore.setVisible(false);

        scoreLabel.setTranslateX(400);
        scoreLabel.setTranslateY(0);
        scoreLabel.setFont(Font.font("Clear Sans", FontWeight.BOLD,30));
        scoreLabel.setTextFill(Color.GRAY);

        scoreText.setTranslateX(500);
        scoreText.setTranslateY(0);
        scoreText.setFont(Font.font("Clear Sans", FontWeight.BOLD,30));
        scoreText.setTextFill(Color.GRAY);

        failLabel.setVisible(false);
        failLabel.setTranslateX(80);
        failLabel.setTranslateY(150);
        failLabel.setTextFill(Color.GRAY);
        failLabel.setFont(Font.font("Clear Sans", FontWeight.BOLD,80));

        listView.setStyle("-fx-font-family: 'Clear-sans'; -fx-font-size: 20px; -fx-text-fill: gray; " +
                "-fx-background-color: transparent; -fx-base: rgba(0,0,0,0);"+
                "-fx-table-header-border-color: transparent; -fx-border-color: transparent; " +
                "-fx-table-cell-border-color: transparent; -fx-control-inner-background: transparent;" +
                "-fx-translate-x: 90; -fx-translate-y: 50; -fx-pref-width: 400; -fx-pref-height: 362;");
        listView.setVisible(false);
        listView.setSelectionModel(null);
    }

    /**
     *
     * Set {@link GaussianBlur} to the game board.
     * @param rectangles MyRectangle matrix.
     * @param texts MyText matrix.
     * @param mode The intensity of blur.
     */
    public void blurON(MyRectangle[][] rectangles, MyText[][] texts, int mode){
        if (mode == 0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rectangles[i][j].setEffect(new GaussianBlur(10));
                    texts[i][j].setEffect(new GaussianBlur(10));
                }
            }
            rectangle.setEffect(new GaussianBlur(10));
        } else if (mode == 1){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rectangles[i][j].setEffect(new GaussianBlur(20));
                    texts[i][j].setEffect(new GaussianBlur(20));
                }
            }
            rectangle.setEffect(new GaussianBlur(20));
        }
    }

    /**
     * Remove the {@link GaussianBlur} on the game board.
     *
     * @param rectangles MyRectangle matrix.
     * @param texts MyText matrix.
     */
    public void blurOFF(MyRectangle[][] rectangles, MyText[][] texts){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                rectangles[i][j].setEffect(new GaussianBlur(0.0));
                texts[i][j].setEffect(new GaussianBlur(0.0));
            }
        }
        rectangle.setEffect(new GaussianBlur(0.0));
    }

    private void initTable() {
        TableColumn name = new TableColumn("Score");
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        TableColumn score = new TableColumn("Date");
        score.setMinWidth(50);
        score.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        name.setStyle("-fx-font-family: 'Clear-sans'; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: gray; -fx-alignment: center; -fx-background-color: transparent;");
        score.setStyle("-fx-font-family: 'Clear-sans'; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: gray; -fx-background-color: transparent;");
        listView.getColumns().addAll(name, score);

        highScoreArrayList = highScoreDAO.getHighScores().getHighscore();
        lista = FXCollections.observableArrayList();
        for (Score highScore : highScoreArrayList) {
            lista.add(highScore);
        }

        listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        listView.setItems(lista);
        listView.refresh();
    }

    /**
     * Refresh the "High Score" table.
     */
    public void refreshTable(){
        highScoreArrayList = highScoreDAO.getHighScores().getHighscore();
        lista = FXCollections.observableArrayList();
        for (Score highScore : highScoreArrayList) {
            lista.add(highScore);
        }
        listView.setItems(lista);
        listView.refresh();
    }
}

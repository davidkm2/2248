package hu.unideb.inf.Controller;

import hu.unideb.inf.Dao.HighScore;
import hu.unideb.inf.Dao.HighScoreDAO;
import hu.unideb.inf.Dao.Score;
import hu.unideb.inf.View.Colors;
import hu.unideb.inf.View.ElementController;
import hu.unideb.inf.View.MyRectangle;
import hu.unideb.inf.View.MyText;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class.
 */
public class Main extends Application{

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static Grid grid = new Grid();
    private static Pane appRoot = new Pane();
    private static Colors colors = new Colors();
    private ElementController elementController = new ElementController();

    /**
     * Actual score.
     */
    public static int score = 0;

    private MyRectangle[][] rectangles = new MyRectangle[Grid.SIZE][Grid.SIZE];
    private MyText[][] texts = new MyText[Grid.SIZE][Grid.SIZE];
    public List<Tile> selected = new ArrayList<Tile>();
    public List<Integer> colcoords = new ArrayList<Integer>();
    public List<Integer> rowcoords = new ArrayList<Integer>();
    private int globali, globalj;


    private static final int WINWIDTH = 600;
    private static final int WINHEIGHT= 600;
    /** Creates a new rectangle with mouse event.*/
    private MyRectangle createRect(int x, int y, int i, int j) {
        MyRectangle temprect = new MyRectangle(x, y);
        temprect.setOnMouseClicked(e -> {
            addtoSelected(i,j);

            // other things you need to do when the mouse hovers....
        });
        // other label configuration...
        return temprect ;
    }
    private Pane initScreen(){
        appRoot.setPrefSize(WINWIDTH, WINHEIGHT);
        appRoot.getChildren().addAll(elementController.rectangle);

        for (int i = 0; i < grid.SIZE; i++){
            for (int j = 0; j < grid.SIZE; j++){
                //MyRectangle temprect = new MyRectangle(100+i*100,100+j*100);
                //temprect.setOnMouseClicked(e -> addtoSelected(i,j));
                rectangles[i][j] = createRect(100+i*100,100+j*100, i, j);
                texts[i][j] = new MyText(125+i*100,115+j*100,0);
                appRoot.getChildren().addAll(rectangles[i][j],texts[i][j]);
            }
        }
        updateTiles();
        appRoot.getChildren().addAll(elementController.failLabel, elementController.newgame, elementController.scoreLabel,
                                     elementController.scoreText, elementController.highscore, elementController.listView,
                                     elementController.back);

        return appRoot;
    }
    /** Checks two tiles. If they are neighbours, return true, else false. IF they are the same tile, return false.*/
    private boolean isNeighbour(int previ, int prevj, int nexti, int nextj){
        //right
        if( (previ == nexti) && (nextj == prevj+1) )
            return true;
        //left
        if( (previ == nexti) && (nextj == prevj-1) )
            return true;
        //above
        if( (nexti == previ-1) && (prevj == nextj))
            return true;
        //below
        if( (nexti == previ+1) && (prevj == nextj))
            return true;
        //diagonal up-right
        if( (nexti == previ-1) && (nextj == prevj+1))
            return true;
        //diagonal up-left
        if( (nexti == previ-1) && (nextj == prevj-1))
            return true;
        //diagonal down-right
        if( (nexti == previ+1) && (nextj == prevj+1))
            return true;
        //diagonal down-left
        if( (nexti == previ+1) && (nextj == prevj-1))
            return true;
        //same
        if( (nexti == previ) && (nextj == prevj))
            return false;
        return false;

    }
    /** After selecting a tile by clicking, it adds it to a selected list*/
    private void addtoSelected(int i, int j) {
        if (selected.size() == 0) {
            selected.add(grid.tiles[i][j]);
            colcoords.add(i);
            rowcoords.add(j);
            logger.debug("Selected");
        }
        if (isNeighbour(colcoords.get(colcoords.size() - 1), rowcoords.get(rowcoords.size() - 1), i, j)){
        if (selected.size() == 1) {
            if (selected.get(0).getValue() == grid.tiles[i][j].getValue()) {
                selected.add(grid.tiles[i][j]);
                colcoords.add(i);
                rowcoords.add(j);
                logger.debug("Selected");
            } else
                logger.debug("Wrong!");
        }
        if (selected.size() >= 2) {
            if ((selected.get(selected.size() - 1).getValue() == grid.tiles[i][j].getValue()) ||
                    (selected.get(selected.size() - 1).getValue()) == grid.tiles[i][j].getValue() / 2) {
                selected.add(grid.tiles[i][j]);
                colcoords.add(i);
                rowcoords.add(j);
                logger.debug("Selected");
            } else
                logger.debug("Wrong!");
        }
    }
    }
    /**Updates the tiles.*/
    private void updateTiles(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                double rectCentX = rectangles[i][j].getTranslateX() + (15);
                rectangles[i][j].setFill(colors.colorMap.get(grid.tiles[i][j].getValue()));
                if (grid.tiles[i][j].getValue()==0){
                    texts[i][j].setText("");
                } else {
                    if(grid.tiles[i][j].getValue() >= 2 && grid.tiles[i][j].getValue() <= 8){
                        texts[i][j].setTranslateX(rectCentX+10);
                    } else if(grid.tiles[i][j].getValue() >= 16 && grid.tiles[i][j].getValue() <= 64){
                        texts[i][j].setTranslateX(rectCentX+2);
                    } else if(grid.tiles[i][j].getValue() >= 128 && grid.tiles[i][j].getValue() <1024){
                        texts[i][j].setTranslateX(rectCentX-8);
                    } else if (grid.tiles[i][j].getValue() >= 1024 ){
                        texts[i][j].setTranslateX(rectCentX-18);
                    }
                    texts[i][j].setText(String.valueOf(grid.tiles[i][j].getValue()));
                }


            }
        }

        elementController.scoreText.setText(String.valueOf(score));
    }


    private void newgame(){
        elementController.newgame.setOnAction(event -> {
            logger.info("New game.");
            grid.resetgame();
            score=0;
            updateTiles();
            elementController.blurOFF(rectangles,texts);
            elementController.failLabel.setVisible(false);
            elementController.newgame.setVisible(false);
            elementController.highscore.setVisible(false);
        });
    }

    private void highScore(){
        elementController.highscore.setOnAction(event -> {
            elementController.blurON(rectangles,texts,1);
            elementController.failLabel.setVisible(false);
            elementController.newgame.setVisible(false);
            elementController.highscore.setVisible(false);
            elementController.listView.setVisible(true);
            elementController.back.setVisible(true);
            initData(score);
            elementController.refreshTable();
        });
    }

    private void back(){
        elementController.back.setOnAction(event -> {
            elementController.back.setVisible(false);
            elementController.blurON(rectangles,texts,0);
            elementController.failLabel.setVisible(true);
            elementController.newgame.setVisible(true);
            elementController.highscore.setVisible(true);
            elementController.listView.setVisible(false);
        });
    }

   /**
    * Save the score and the actual date to the database.
    * @param score Actual score.
    */
    public static void initData(int score) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        Score score1 = new Score(String.valueOf(score),formattedDateTime);
        HighScoreDAO higshScoreDAO = new HighScoreDAO();

        HighScore highScore = new HighScore();
        highScore = higshScoreDAO.getHighScores();

        for ( Score score2 : highScore.getHighscore()){
            if (String.valueOf(score2).equals(score) && dateTime.equals(score2.getDate())){

            } else {

            }
        }

        higshScoreDAO.addScore(score1);

    }
    /**
     * Merges the selected tiles, and cleans the lists
     *
     */
    public void merge(){
        int result = 0;
        if(selected.size() >=2) {
            for (int i = 0; i < selected.size(); i++) {
                result += selected.get(i).getValue();
                score += selected.get(i).getValue();
            }

            for (int i = 0; i < rowcoords.size(); i++) {
                grid.tiles[rowcoords.get(i)][colcoords.get(i)].setValue(grid.getNewTileValue());
                texts[colcoords.get(i)][rowcoords.get(i)].setText(String.valueOf(grid.tiles[colcoords.get(i)][rowcoords.get(i)].getValue()));

            }
            int setcol = rowcoords.get(rowcoords.size() - 1);
            int setrow = colcoords.get(colcoords.size() - 1);
            grid.tiles[setrow][setcol].setValue(result * 2);
            texts[setrow][setcol].setText(String.valueOf(grid.tiles[setrow][setcol]));

            selected.removeAll(selected);
            colcoords.removeAll(colcoords);
            rowcoords.removeAll(rowcoords);
        }
        updateTiles();
        logger.debug("Merged all selected tiles, cleaned selected and coords lists.");
    }
    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(initScreen());

        scene.setOnKeyPressed(event -> {
            if (grid.noPossibleMove()){
                logger.info("No possible moves.");
                elementController.blurON(rectangles,texts,0);
                elementController.failLabel.setVisible(true);
                elementController.newgame.setVisible(true);
                elementController.highscore.setVisible(true);
                newgame();
                highScore();
                back();
            } else {
                elementController.blurOFF(rectangles,texts);
                if (event.getCode() == KeyCode.ESCAPE){
                    logger.debug("ESC");
                    selected.clear();
                    rowcoords.clear();
                    colcoords.clear();
                }
                else if (event.getCode() == KeyCode.ENTER){
                    logger.debug("ENTER");
                    merge();
                }
                else if (event.getCode() == KeyCode.SPACE){
                    for(int i=0; i<grid.SIZE; i++){
                        for(int j=0; j<grid.SIZE; j++){
                            grid.tiles[i][j].setValue(i+j*2);
                        }
                    }
                    updateTiles();
                }
            }
        });

        stage.setTitle("2248");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method.
     * @param args System args.
     */
    public static void main(String[] args) {
        logger.info("Game started.");
        launch(args);
    }
}

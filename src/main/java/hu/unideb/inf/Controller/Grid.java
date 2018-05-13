package hu.unideb.inf.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;




/**
 * The Grid class implements the main grid of the 2048 game.
 */
public class Grid {
    public int getRandom(List<Integer> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }


    /** The size of the grid.*/
    public static final int SIZE = 4;

    /** All the tiles.*/
    public Tile[][] tiles = new Tile[SIZE][SIZE];

    /** Constructor of the Grid class.*/
    public Grid(){
        //szamok = new int[9](2,2,2,2,4,4,4,8,8);
        List<Integer> szamok = new ArrayList<>();
        szamok.add(2);
        szamok.add(2);
        szamok.add(2);
        szamok.add(4);
        szamok.add(4);
        szamok.add(8);
        for (int i = 0; i < tiles[0].length; i++){
            for (int j = 0; j < tiles.length; j++){
                tiles[i][j] = new Tile();
                int randomszam = getRandom(szamok);
                tiles[i][j].setValue(randomszam);
            }
        }
        /** Resets the game.*/
    }
    public void resetgame(){
        List<Integer> szamok = new ArrayList<>();
        szamok.add(2);
        szamok.add(2);
        szamok.add(2);
        szamok.add(4);
        szamok.add(4);
        szamok.add(8);
        for (int i = 0; i < tiles[0].length; i++){
            for (int j = 0; j < tiles.length; j++){
                tiles[i][j] = new Tile();
                int randomszam = getRandom(szamok);
                tiles[i][j].setValue(randomszam);
            }
        }
    }
    /**
     * This method gives value to an empty tile.
     * @return true.
     */
    public boolean generateNewTile(){
        for(Tile[] tilek : tiles){
            for(Tile tile : tilek){
                if (!(hasEmptyTile())){
                    return false;
                }


                if (tile.getValue() == 0){
                    tile.setValue(getNewTileValue());
                    return true;
                }
                return true;
            }
        }
        return true;
    }
    /** Generating a new, random value from 2, 4, or 8.*/
    public int getNewTileValue(){
        List<Integer> szamok = new ArrayList<>();
        szamok.add(2);
        szamok.add(2);
        szamok.add(2);
        szamok.add(4);
        szamok.add(4);
        szamok.add(8);
        return getRandom(szamok);
    }



    private boolean isEmptyTile(List<Tile> tileSet) {
        for (Tile tile: tileSet){
            if (tile.getValue() != 0){
                return false;
            }
        }
        return true;
    }

    /** Old merge. I left it here just in case.*/

   /* private void mergeTile(List<Tile> tileSet){
        for (int i = 0; i < tileSet.size() - 1; i++){
            if (tileSet.get(i).equals(tileSet.get(i + 1))){
                tileSet.get(i).merge(tileSet.get(i + 1));
                tileSet.get(i + 1).clear();
                Main.score+=tileSet.get(i).getValue();

            }
            if(tileSet.get(i).twice(tileSet.get(i+1))){
                tileSet.get(i).merge(tileSet.get(i+1));
                tileSet.get(i+1).clear();
                slideTo(tileSet, i+1);
                Main.score+=tileSet.get(i).getValue();
            }
        }
    }*/

    /**
     * Check the grid to is has possible moves.
     * @return true.
     */
    public boolean noPossibleMove(){
        return (!(hasEqualNeighbour()));
    }

    /**
     * Check the value of the tile.
     * The grid has no possible moves, when there is not equal neighbours
     * @return true.
     */
    public boolean hasEmptyTile(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (tiles[i][j].getValue() == 0){
                    return true;
                }
            }
        }
        return false;
    }
    /** Checks the grid has equal neighbours.*/
    private boolean hasEqualNeighbour() {
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (j < SIZE - 1){
                    if (tiles[i][j].equals(tiles[i][j + 1])){
                        return true;
                    }
                }

                if (i < SIZE - 1){
                    if (tiles[i][j].equals(tiles[i + 1][j])){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /** Set 0 value for every tile.*/
    public void resetGrid(){
        for (int i = 0; i < tiles[0].length; i++){
            for (int j = 0; j < tiles.length; j++){
                tiles[i][j].setValue(0);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Tile[] tileRow: tiles){
            for (Tile tile: tileRow){
                sb.append(tile);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
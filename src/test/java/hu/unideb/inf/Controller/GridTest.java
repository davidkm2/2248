package hu.unideb.inf.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    static Grid grid;

    @Before
    public void initGrid(){
        grid = new Grid();
    }

    @Test
    public void testBoard(){
        assertNotNull(grid);
    }

    @Test
    public void testnewTile(){
        int a = 0, b = 0;
        for (int i = 0; i < grid.tiles.length; i++) {
            for (int j = 0; j < grid.tiles.length; j++) {
                a += grid.tiles[i][j].getValue();
            }
        }
        grid.tiles[1][1].setValue(22);
        grid.generateNewTile();

        for (int i = 0; i < grid.tiles.length; i++) {
            for (int j = 0; j < grid.tiles.length; j++) {
                b += grid.tiles[i][j].getValue();
            }
        }
        assertNotEquals(a,b);
    }

    @Test
    public void nothasEmptyTest(){
        assertTrue(!(grid.hasEmptyTile()));
    }

    @Test
    public void noMovesTest(){
        assertFalse(grid.noPossibleMove());
    }

    @Test
    public void resetBoard(){
        grid.resetGrid();
        for (int i = 0; i < grid.tiles.length; i++) {
            for (int j = 0; j < grid.tiles.length; j++) {
                assertEquals(0,grid.tiles[i][j].getValue());
            }
        }
    }
}

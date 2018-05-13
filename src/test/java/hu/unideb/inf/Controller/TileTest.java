package hu.unideb.inf.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    static Tile tile;

    @Before
    public void initTile(){
        tile = new Tile();
    }

    @Test
    public void testTile(){
        assertNotNull(tile);
    }

    @Test
    public void setTileTest(){
        tile.setValue(2);
        assertEquals(2,tile.getValue());
    }

    @Test
    public void  getTileTest(){
        int a = tile.getValue();
        assertEquals(0, a);
    }

    @Test
    public void equalsTest(){
        Tile a = new Tile(2);
        assertFalse(tile.equals(a));
    }

    @Test
    public void mergeTest(){
        Tile a = new Tile(2);
        tile.merge(a);
        assertEquals(2,tile.getValue());
    }

    @Test
    public void clearTileTest(){
        tile.clear();
        assertEquals(0,tile.getValue());
    }

    @Test
    public void toStringTest(){
        assertEquals("0",tile.toString());
    }
}

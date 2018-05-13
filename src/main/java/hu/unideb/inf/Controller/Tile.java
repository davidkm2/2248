package hu.unideb.inf.Controller;

/**
 * The Tile class implements the tiles of the 2048 game.
 *
 * @author John David Balogh
 */

public class Tile {

    private int value;

    /**
     * The constructor of the class which set the tile 0.
     */

    public Tile(){
        this(0);
    }

    /**
     * This constructor with value parameter.
     * This sets the value of tile.
     *
     * @param value The value of the tile.
     */

    public Tile(int value){
        this.value = value;
    }

    /**
     * Set the value.
     *
     * @param value The value of the class.
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Get the value.
     *
     * @return value
     */

    public int getValue(){
        return value;
    }

    /**
     * Check the tile value is equals the parameter value of the {@link Tile}.
     *
     * @param tile The examined {@link Tile}.
     * @return true if the two values are equals.
     */

    public boolean equals(Tile tile){
        return tile.getValue() == this.getValue();
    }
    /**
     * Check the tile value is twice of the value of the {@link Tile}.
     *
     * @param tile The examined {@link Tile}.
     * @return true if the examined tile's value is twice as the value of the tile.
     */
    public boolean twice(Tile tile){
        return tile.getValue() == this.getValue()*2;
    }
    /**
     * Returns the value of two tiles.
     *
     * @param tile The examined {@link Tile}.
     */

    public void merge(Tile tile){
        this.setValue(value + tile.getValue());
    }

    /**
     * Clear the tile. Set value of the tile to 0.
     */

    public void clear(){
        this.setValue(0);
    }

    @Override
    public String toString(){
        return (Integer.toString(this.getValue()));
    }
}

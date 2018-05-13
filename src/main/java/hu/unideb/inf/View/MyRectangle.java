package hu.unideb.inf.View;

import javafx.scene.shape.Rectangle;

/**
 * The MyRectangle class defines a {@link Rectangle} with the
 * specified size, location, color and rounded corners.
 *
 * @author Aniko Apro
 */

public class MyRectangle extends Rectangle{

    private Colors colors = new Colors();
    private int x, y;

    /**
     * Creates a new instance of Rectangle with the given position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public MyRectangle(int x, int y) {
        super(70,70);
        super.setFill(colors.colorMap.get(0));
        this.x = x;
        this.y = y;
        super.setTranslateX(x);
        super.setTranslateY(y);
        super.setArcWidth(30);
        super.setArcHeight(30);
    }
}

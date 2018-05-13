package hu.unideb.inf.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The MyText class defines a node that displays a text.
 *
 * @author John David Balogh
 * */
public class MyText extends Label{

    /**
     * Creates an instance of Text on the given coordinates containing the given string.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param text The value of {@link hu.unideb.inf.Controller.Tile}
     */
    public MyText(double x, double y, int text) {
        super(String.valueOf(text));
        super.setTranslateX(x);
        super.setTranslateY(y);
        super.setFont(Font.font("Clear Sans", FontWeight.BOLD,30));
        super.setTextFill(Color.WHITE);
        super.setAlignment(Pos.CENTER);
    }
}

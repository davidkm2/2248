package hu.unideb.inf.View;

import hu.unideb.inf.Controller.Tile;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * The Colors class makes a "dictionary".
 * In all rectangles we can match a color to the all selectable numbers.
 *
 * @author John David Balogh
 */

public class Colors {

    /**
     * Dictionary is a {@link Map} collection that contains {@link Integer} as
     * key and {@link Color} as value.
     */
    public Map<Integer, Color> colorMap = new HashMap<Integer, Color>();

    /**
     * The constructor of the {@link Color} class.
     */

    public Colors() {
        putColors();
    }

    /**
     * Associates the recordable value of {@link Tile} with
     * a specified {@link Color} in this map.
     * */
    private void putColors(){
        colorMap.put(0, Color.rgb(238, 228, 216));
        colorMap.put(2, Color.rgb(238, 228, 218));
        colorMap.put(4, Color.rgb(237, 224, 200));
        colorMap.put(8, Color.rgb(242, 177, 121));
        colorMap.put(16, Color.rgb(245, 149, 99));
        colorMap.put(32, Color.rgb(246, 124, 95));
        colorMap.put(64, Color.rgb(246, 94, 59));
        colorMap.put(128, Color.rgb(237, 207, 114));
        colorMap.put(256, Color.rgb(237, 204, 97));
        colorMap.put(512, Color.rgb(237, 200, 80));
        colorMap.put(1024, Color.rgb(237, 197, 63));
        colorMap.put(2048, Color.rgb(237, 194, 46));
    }
}

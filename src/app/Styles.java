package app;


import jGameLib.ui2d.utils.TextStyle;
import jGameLib.ui2d.utils.TextStyleBuilder;
import jGameLib.util.files.FontLoader;

import java.awt.*;

public class Styles {
    public static Color setAlpha(Color color, int i) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), i);
    }

    public static TextStyle titleText =
            new TextStyleBuilder()
                    .setFont(
                            FontLoader.load("font/Copperplate-Gothic-Std-29-BC.ttf")
                    ).setAlignment(
                            TextStyle.TextAlign.CENTER
                    ).setColor(
                            new Color(0xf3d318)
                    ).setFontSize(
                            80f
                    )
                    .get();
}

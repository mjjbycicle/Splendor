package app.constants;


import jGameLib.ui2d.utils.TextStyle;
import jGameLib.ui2d.utils.TextStyleBuilder;
import jGameLib.util.files.FontLoader;

import java.awt.Color;

public class Styles {
    public static java.awt.Color setAlpha(java.awt.Color color, int i) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), i);
    }

    private static TextStyleBuilder builder = new TextStyleBuilder()
                    .setFont(
                            FontLoader.load("font/Copperplate-Gothic-Std-29-BC.ttf")
                    ).setAlignment(
                            TextStyle.TextAlign.CENTER
                    ).setColor(
                            new Color(0xf3d318)
                    );

    public static TextStyle titleText = builder.setFontSize(80f).get();

    public static TextStyle inactiveNameText = builder.setFontSize(45f).get();

    public static TextStyle activeNameText = builder.setFontSize(60f).get();
}

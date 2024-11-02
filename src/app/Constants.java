package app;


import jGameLib.util.math.Vec2;

public class Constants {
    private static final Vec2 MIDDLE_LOCATION = new Vec2(960, 540);

    public static class TitleScreenConstants {
        public static final Vec2 TITLE_LOCATION = new Vec2(960, 330).minus(MIDDLE_LOCATION);
        public static final Vec2 TITLE_SIZE = new Vec2(960, 390);
        public static final Vec2 START_BUTTON_LOCATION = new Vec2(960, 581).minus(MIDDLE_LOCATION);
        public static final Vec2 START_BUTTON_SIZE = new Vec2(393, 63);
        public static final Vec2 RULES_BUTTON_LOCATION = new Vec2(960, 745).minus(MIDDLE_LOCATION);
    }

    public static class TurnScreenConstants {

    }
}

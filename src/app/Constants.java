package app;

import lib.math.Vec2;

public class Constants {
    private static final Vec2 MIDDLE_LOCATION = new Vec2(960, 540);
    public static final Vec2 TITLE_LOCATION = new Vec2(960, 330).minus(MIDDLE_LOCATION);
    public static final Vec2 TITLE_SIZE = new Vec2(960, 390).minus(MIDDLE_LOCATION);
    public static final Vec2 START_BUTTON_LOCATION = new Vec2(960, 581).minus(MIDDLE_LOCATION);

}

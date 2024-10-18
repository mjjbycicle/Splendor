import app.App;

public class Main {
    public static void main(String[] args) {
        lib.engine.GameEngine.enableHardwareAcceleration();
        new App().run();
    }
}
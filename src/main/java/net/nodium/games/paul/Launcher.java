package net.nodium.games.paul;

public class Launcher {
    public static Game game;

    public static void main(String[] args) {
        game = new Game(args);
        game.start();
    }
}

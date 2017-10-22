package net.krydd;

import net.krydd.core.GameEngine;
import net.krydd.core.GameWindow;
import net.krydd.core.PlayerInput;

public class Main {

    public static void main(String[] args) {
        PlayerInput playerInput = new PlayerInput();
        final GameWindow gameWindow = new GameWindow();
        final GameEngine engine = new GameEngine(playerInput);

        gameWindow.addKeyListener(playerInput);
        gameWindow.setGameCanvas(engine.getCanvas());
        engine.initGame();
        engine.run();
    }
}

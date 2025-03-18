package hello.mission.minesweeper;

import hello.mission.minesweeper.config.GameConfig;
import hello.mission.minesweeper.gamelevel.Beginner;
import hello.mission.minesweeper.io.ConsoleInputHandler;
import hello.mission.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
            new Beginner(),
            new ConsoleInputHandler(),
            new ConsoleOutputHandler()
        );

        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }

}

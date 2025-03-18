package hello.mission.minesweeper.io;

import hello.mission.minesweeper.board.position.CellPosition;
import hello.mission.minesweeper.user.UserAction;

public interface InputHandler {

    UserAction getUserActionFromUser();

    CellPosition getCellPositionFromUser();

}

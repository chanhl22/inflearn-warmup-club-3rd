package hello.mission.minesweeper.io.sign;

import hello.mission.minesweeper.board.cell.CellSnapshot;
import hello.mission.minesweeper.board.cell.CellSnapshotStatus;

public class LandMineCellSignProvider implements CellSignProvidable {

    private static final String LAND_MINE_SIGN = "☼";

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.LAND_MINE);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return LAND_MINE_SIGN;
    }

}

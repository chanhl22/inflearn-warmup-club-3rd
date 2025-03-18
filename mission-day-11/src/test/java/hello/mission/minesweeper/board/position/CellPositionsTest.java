package hello.mission.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CellPositionsTest {

    @DisplayName("요청한 숫자만큼 랜덤한 Cell 위치 리스트를 추출한다.")
    @Test
    void extractRandomPositions() {
        //given
        int count = 3;
        List<CellPosition> positions = List.of(
                CellPosition.of(0, 0),
                CellPosition.of(0, 1),
                CellPosition.of(0, 2),
                CellPosition.of(0, 3)
        );
        CellPositions cellPositions = CellPositions.of(positions);

        //when
        List<CellPosition> result = cellPositions.extractRandomPositions(count);

        //then
        assertThat(result).hasSize(count);
    }

    @DisplayName("전체 Cell 위치 리스트에서 요청한 Cell 위치 리스트를 덜어낸다.")
    @Test
    void subtract() {
        //given
        List<CellPosition> positions = List.of(
                CellPosition.of(0, 0),
                CellPosition.of(0, 1),
                CellPosition.of(0, 2),
                CellPosition.of(0, 3)
        );
        List<CellPosition> positionListToSubtract = List.of(
                CellPosition.of(0, 0)
        );
        CellPositions cellPositions = CellPositions.of(positions);

        //when
        List<CellPosition> result = cellPositions.subtract(positionListToSubtract);

        //then
        assertThat(result).hasSize(3);
    }

}
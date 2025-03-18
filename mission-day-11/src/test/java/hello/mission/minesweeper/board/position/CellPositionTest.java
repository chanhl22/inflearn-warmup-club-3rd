package hello.mission.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellPositionTest {

    @DisplayName("Cell 좌표를 만든다.")
    @Test
    void of() {
        //given
        int rowIndex = 0;
        int colIndex = 0;

        //when
        CellPosition result = CellPosition.of(rowIndex, colIndex);

        //then
        assertThat(result.getRowIndex()).isEqualTo(rowIndex);
        assertThat(result.getColIndex()).isEqualTo(colIndex);
    }

    @DisplayName("Cell 좌표가 0보다 작다면 예외가 발생한다.")
    @Test
    void lessThan0() {
        //given
        int rowIndex = -1;
        int colIndex = -1;

        //when //then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("상대 좌표를 사용해 계산한 후 유효한 좌표인지 확인한다.")
    @Test
    void canCalculatePositionBy() {
        //given
        int rowIndex = 0;
        int colIndex = 0;
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition1 = RelativePosition.of(-1, -1);
        RelativePosition relativePosition2 = RelativePosition.of(0, 0);

        //when
        boolean result1 = cellPosition.canCalculatePositionBy(relativePosition1);
        boolean result2 = cellPosition.canCalculatePositionBy(relativePosition2);

        //then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
    }

    @DisplayName("상대 좌표를 사용해 좌표를 계산한다.")
    @Test
    void calculatePositionBy() {
        //given
        int rowIndex = 0;
        int colIndex = 0;
        int deltaRow = 0;
        int deltaCol = 0;
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //when
        CellPosition result = cellPosition.calculatePositionBy(relativePosition);

        //then
        assertThat(result.getRowIndex()).isEqualTo(rowIndex + deltaRow);
        assertThat(result.getColIndex()).isEqualTo(colIndex + deltaCol);
    }

    @DisplayName("상대 좌표를 사용해 계산한 좌표가 0보다 작다면 예외가 발생한다.")
    @Test
    void calculatePositionLessThan0() {
        //given
        int rowIndex = 0;
        int colIndex = 0;
        int deltaRow = -1;
        int deltaCol = -1;
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        //when //then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

}
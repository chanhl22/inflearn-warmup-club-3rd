package hello.mission.minesweeper.io.sign;

import hello.mission.minesweeper.board.cell.CellSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellSignProviderTest {

    @DisplayName("Cell의 스냅샷으로 표기할 기호를 찾는다.")
    @Test
    void findCellSignFrom() {
        //given
        CellSnapshot emptySnapshot = CellSnapshot.ofEmpty();
        CellSnapshot flagSnapshot = CellSnapshot.ofFlag();
        CellSnapshot landMineSnapshot = CellSnapshot.ofLandMine();
        CellSnapshot numberSnapshot = CellSnapshot.ofNumber(2);
        CellSnapshot uncheckedSnapshot = CellSnapshot.ofUnchecked();

        //when
        String emptySign = CellSignProvider.findCellSignFrom(emptySnapshot);
        String flagSign = CellSignProvider.findCellSignFrom(flagSnapshot);
        String landmineSign = CellSignProvider.findCellSignFrom(landMineSnapshot);
        String numberSign = CellSignProvider.findCellSignFrom(numberSnapshot);
        String uncheckedSign = CellSignProvider.findCellSignFrom(uncheckedSnapshot);

        //then
        assertThat(emptySign).isEqualTo("■");
        assertThat(flagSign).isEqualTo("⚑");
        assertThat(landmineSign).isEqualTo("☼");
        assertThat(numberSign).isEqualTo("2");
        assertThat(uncheckedSign).isEqualTo("□");
    }

    @DisplayName("일치하는 Cell의 스냅샷이 없으면 예외가 발생한다.")
    @Test
    void nonMatchCellSignFrom() {
        //given
        CellSnapshot snapshot = CellSnapshot.of(null, 0);

        //when //then
        assertThatThrownBy(() -> CellSignProvider.findCellSignFrom(snapshot))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("확인할 수 없는 셀입니다.");
    }

}
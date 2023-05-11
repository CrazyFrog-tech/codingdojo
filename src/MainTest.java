import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MainTest {
    @Test
    public void geeftLegeLijstTerugAlsEenLegeLijstWordtMeegegeven() {
        int[] input = new int[0];

        Map<String, List<Integer>> actual = Main.getPeaks(input);

        Map<String, List<Integer>> expected = Map.of(
            "pos", List.of(),
            "peaks", Collections.emptyList());
        assertThat(actual).describedAs("should return an empty list").isEqualTo(expected);
    }

    @Test
    public void vindtPieken() {
        int[] input = { 1, 2, 3, 6, 4, 1, 2, 3, 2, 1 };

        Map<String, List<Integer>> actual = Main.getPeaks(input);

        Map<String, List<Integer>> expected = Map.of(
            "pos", List.of(3, 7),
            "peaks", List.of(6, 3));
        assertEquals("should support finding peaks", expected, actual);

    }

    @Test
    public void vindtPiekenEnNegeertEersteEnLaatsteElement() {
        int[] input = { 3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3 };

        Map<String, List<Integer>> actual = Main.getPeaks(input);
        Map<String, List<Integer>> expected = Map.of(
            "pos", List.of(3, 7),
            "peaks", List.of(6, 3));
        assertEquals("should support finding peaks, but should ignore peaks on the edge of the array", expected, actual);

    }
    static DataSet[] plateauGevallenSupplier() {
        return new DataSet[] {
            new DataSet("should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
                List.of( 3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 2, 2, 1 ),
                Map.of("pos", List.of(3, 7, 10), "peaks", List.of(6, 3, 2))),

            new DataSet("should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
                List.of(2, 1, 3, 1, 2, 2, 2, 2, 1),
                Map.of("pos", List.of( 2, 4), "peaks", List.of(3, 2))),

            new DataSet("should support finding peaks, but should ignore peaks on the edge of the array",
                List.of(2, 1, 3, 1, 2, 2, 2, 2 ),
                Map.of("pos", List.of( 2), "peaks", List.of(3)))

        };
    }
    record DataSet(String description, List<Integer> input, Map<String, List<Integer>> expectation) {
    }
    @ParameterizedTest
    @MethodSource("plateauGevallenSupplier")
    public void vindtHetEersteGetalInHetGevalVanPlateauEnNegeertEersteEnlaatsteEelement(DataSet dataset) {

        int[] input = dataset.input().stream().mapToInt(i -> i).toArray();
        Map<String, List<Integer>> actual = Main.getPeaks(input);

        assertEquals(dataset.description(), dataset.expectation(), actual);
    }


}


package algo1.pas.pa1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

/**
 * Created by ilyarudyak on 7/19/16.
 */
@RunWith(JUnitParamsRunner.class)
public class InversionTest {

    private static final Object[] getInversions() {
        return $(
                $(3, new int[]{1, 3, 5, 2, 4, 6}),
                $(5, new int[]{1, 20, 6, 4, 5}),
                $(3, new int[]{2, 4, 1, 3, 5})
        );
    }

    @Test
    @Parameters(method = "getInversions")
    public void testInversionsCount(int inversions, int[] a) {
        Inversion inversion = new Inversion(a);
        inversion.sort();
        assertEquals(inversions, inversion.getInversions());
    }

}













package hackerearth;

import org.junit.Test;

import static org.junit.Assert.*;

public class RescueOpsTest {

    @Test
    public void testSolve() {
        System.out.println(RescueOps.solve(4, "MMLM"));
        System.out.println(RescueOps.solve(6, "LMRMLM"));
        System.out.println(RescueOps.solve(6, "MMMMMM"));
        System.out.println(RescueOps.solve(6, "LRLR"));
    }

}
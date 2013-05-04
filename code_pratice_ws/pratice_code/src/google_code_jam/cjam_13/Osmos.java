package google_code_jam.cjam_13;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Osmos {

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo=1;testNo<=tests;testNo++) {
			int arminMote = in.nextInt();

			int motes = in.nextInt();
			int[] moteSizes = new int[motes];

			for (int i=0;i<motes;i++) {
				moteSizes[i] = in.nextInt();
			}

			long operations = getMinimumOperations(arminMote,moteSizes);

			out.println("Case #" + testNo + ": " + operations);
		}
	}


	private long getMinimumOperations(int arminMote, int[] moteSizes) {
		long operations = 0;

		Arrays.sort(moteSizes);

		operations = getOperationsRecurse(0, arminMote, moteSizes, 0);

		return operations;
	}

	// this worked only for smaller inputs.
	private long getOperationsRecurse(int index, int arminMote, int[] moteSizes, long operations) {
		if (index==moteSizes.length-1) {
			if (moteSizes[index] < arminMote) {
				return operations;
			}
			return operations + 1;
		}

		//check if we can eat this mote
		long oper3 = Integer.MAX_VALUE;
		if (moteSizes[index] < arminMote) {
			oper3 = getOperationsRecurse(index+1, arminMote+moteSizes[index], moteSizes, operations);
			return oper3;
		}

		// add a mote and try again
		long oper1 = Integer.MAX_VALUE;
		if (arminMote > 1) {
			oper1 = getOperationsRecurse(index, arminMote + arminMote-1, moteSizes, operations+1);
		}

		// or drop the current mote
		long oper2 = getOperationsRecurse(index+1, arminMote, moteSizes, operations+1);

		operations = Math.min(oper3,Math.min(oper1, oper2));

		return operations;
	}
}

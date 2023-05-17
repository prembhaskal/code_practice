package google_code_jam.cjam_13;

import common.util.InputReader;
import java.io.PrintWriter;

//http://code.google.com/codejam/contest/2418487/dashboard#s=p1
public class ManageYourEnergy {

	int totalEnergy;
	int regainEnergy;
	int N;

	int[] valueOfwork;


	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo = 1;testNo<=tests;testNo++) {
			totalEnergy = in.nextInt();
			regainEnergy = in.nextInt();
			N = in.nextInt();
			valueOfwork = new int[N];

			for (int i=0;i<N;i++) {
				valueOfwork[i] = in.nextInt();
			}

			int maxEnergy = getMaxEnergyGain();

			out.println("Case #" + testNo + ": " + maxEnergy);
		}
	}

	// TODO try to solve for big input all by yourself first.
	public int getMaxEnergyGain() {
		int maxGain = getMaxEnergyRecurse(0,0,totalEnergy);
		return maxGain;
	}

	// this solution works for small input only.
	private int getMaxEnergyRecurse(int index, int gain, int energyLeft) {
		// for last work, we spend entire energy left
		if (index==N-1) {
			return gain + valueOfwork[index]*energyLeft;
		}

		int maxGain = -1;

		// we may choose not to spend anything on present work.
		for (int spend = 0;spend<=energyLeft;spend++) {
			int newEnergy = Math.min(energyLeft-spend+regainEnergy, totalEnergy);
			int newGain = getMaxEnergyRecurse(
											index+1,
											gain + valueOfwork[index]*spend,
											newEnergy);
			maxGain = Math.max(maxGain, newGain);
		}

		return maxGain;
	}
}

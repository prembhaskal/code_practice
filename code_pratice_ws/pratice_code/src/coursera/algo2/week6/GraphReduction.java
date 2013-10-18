package coursera.algo2.week6;

import common.util.InputReader;

/**
 * Pre - Process the graph
 * remove the nodes which are not used in any of the clauses.
 *
 * if a variable, in all the clauses where it is used has the same sign, then we can remove these signs
 *
 * eg. -3 is used in only 3 clauses as given below.
 * 1 -3
 * 5 -3
 * -7 -3
 *
 * we can remove all these clauses since putting -3 to true, satisfies all the equation
 *
 * This class does that
 *
 */
public class GraphReduction {

	int clauses;
	int[][] clauseArray;

	boolean[] deletedClauses;

	boolean[] verticesUsed;
	boolean[] verticesComplementUsed;

	public int reduceTheGraph(InputReader in) {
		clauses = in.nextInt();
		clauseArray = new int[clauses][2];

		deletedClauses = new boolean[clauses];
		readGraph(in);
		int totalDeleted = 0;

		while (true) {
			initUsedArray();
			int deleted = markRedundantClauses();
			if (deleted <= 0)
				break;

			totalDeleted += deleted;
		}


		return clauses - totalDeleted;
	}

	private void readGraph(InputReader in) {
		for (int i = 0; i < clauses; i++) {
			clauseArray[i][0] = in.nextInt();
			clauseArray[i][1] = in.nextInt();
		}
	}

	private void initUsedArray() {
		verticesUsed = new boolean[clauses+1]; // +1 since we count nodes from 1
		verticesComplementUsed = new boolean[clauses+1];
		// iterate over the clauses.
		// if you encounter the node, set the VerticesUsed[node] to true.
		// if you encounter the negative node, set the VerticesComplement[node] = true;
		for (int i = 0; i < clauses; i++) {
			if (deletedClauses[i])
				continue;
			int num1 = clauseArray[i][0];
			int num2 = clauseArray[i][1];

			setUsed(num1);
			setUsed(num2);
		}

	}

	private void setUsed(int num) {
		if (num > 0)
			verticesUsed[num] = true;
		else
			verticesComplementUsed[-num] = true;
	}

	private int markRedundantClauses() {
		int totalDeleted = 0;
		for (int i = 0; i < clauses; i++) {
			if (deletedClauses[i]) // added check for running multiple times.
				continue;
			int num1 = clauseArray[i][0];
			int num2 = clauseArray[i][1];

			boolean c1 = checkIfUsedWithBothSigns(num1);
			boolean c2 = checkIfUsedWithBothSigns(num2);

			if (!c1 || !c2) {
				deletedClauses[i] = true;
				totalDeleted++;
			}
		}

		return totalDeleted;
	}

	//  just check if the use of the opposite sign is present.. that is sufficient.
	private boolean checkIfUsedWithBothSigns(int num) {
		if (num > 0) {
			return verticesComplementUsed[num];
		} else
			return verticesUsed[-num];
	}
}

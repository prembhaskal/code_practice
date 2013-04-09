package codeforces.task.m178.div2.task_a;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}

}

class TaskA {

	int wires;
	int[] birds;
	int shots;
	int[] shotWire;
	int[] shotBird;

	public void solve(Scanner in, PrintWriter out) {
		wires = in.nextInt();
		birds = new int[wires+1];
		for (int i=1;i<birds.length;i++) {
			birds[i] = in.nextInt();
		}

		shots = in.nextInt();
		shotWire = new int[shots];
		shotBird = new int[shots];

		for (int i=0;i<shots;i++) {
			shotWire[i] = in.nextInt();
			shotBird[i] = in.nextInt();
		}

		implementShots();

		for (int i=1;i<wires+1;i++) {
			out.println(birds[i]);
		}

	}

	private void implementShots() {

		for (int i=0;i<shots;i++) {
			int totalBirds = birds[shotWire[i]];
			int birdShot = shotBird[i];

			int left = Math.max(birdShot - 1, 0);
			int right = Math.max(totalBirds - left - 1, 0);

			birds[shotWire[i]] = 0;

			if (shotWire[i] > 1) {
				birds[shotWire[i] - 1] += left;
			}

			if (shotWire[i] < wires) {
				birds[shotWire[i] + 1] += right;
			}
		}

	}

}


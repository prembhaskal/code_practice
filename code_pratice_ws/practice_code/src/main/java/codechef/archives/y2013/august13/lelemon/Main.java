package codechef.archives.august13.lelemon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int rooms = in.nextInt();
			int visits = in.nextInt();

			// 0 based visit
			int[] visitArray = new int[visits];
			for (int j = 0; j < visits; j++) {
				visitArray[j] = in.nextInt();
			}

			// create Rooms
			// 0 based index
			Room[] roomArray = new Room[rooms];

			for (int j = 0; j < rooms; j++) {
				int lemonadesInRoom = in.nextInt();
				int[] lemonades = new int[lemonadesInRoom];
				for (int k = 0; k < lemonadesInRoom; k++) {
					lemonades[k] = in.nextInt();
				}

				roomArray[j] = new Room(lemonades);
			}

			int total = getTotalLemonade(visitArray, roomArray);
			out.println(total);

		}

	}


	private int getTotalLemonade(int[] visits, Room[] rooms) {
		int total = 0;

		for (int i = 0; i < visits.length; i++) {
			int roomNumber = visits[i];

			Room room = rooms[roomNumber];
			int lemonade = room.getLemonade();
			total += lemonade;
		}

		return total;
	}

}

class Room {
	private int[] lemonade;
	private int pointer;

	public Room(int[] lemonade) {
		this.lemonade = lemonade;
		pointer = lemonade.length-1;
		Arrays.sort(lemonade);
	}

	public int getLemonade() {
		if (pointer < 0)
			return 0;
		int vol = lemonade[pointer--];
		return vol;
	}

}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}

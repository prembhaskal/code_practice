package fb.hackercup.cup2013_2;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class Basketball {

	int totalPlayers;
	int totalGameTime;
	int playersAllowed;

	Player[] players;

	public void solve(InputReader in, PrintWriter out){
		int tests = in.nextInt();

		for (int i = 1; i <= tests; i++) {
			totalPlayers = in.nextInt();
			totalGameTime = in.nextInt();
			playersAllowed = in.nextInt();

			players = new Player[totalPlayers];


			for (int j = 0; j < totalPlayers; j++) {
				String name = in.next();
				int shotPercent = in.nextInt();
				int height = in.nextInt();

				players[j] = new Player(j, shotPercent, height, name);
			}

			// get the name of playing players.
			List<String> playing = getPlayingPlayers();
			out.print("Case #" + i + ": ");

			for (int j = 0; j < 2 * playersAllowed - 1; j++) {
				out.print(playing.get(j) + " ");
			}

			out.println(playing.get(2*playersAllowed - 1));
		}
	}

	private List<String> getPlayingPlayers() {
		// sort the players.
		Arrays.sort(players);
		// assign the rank.
		for (int i = 0; i < players.length; i++) {
			players[i].rank = i+1;
		}

		// distribute the players.
		List<Player> players1 = new ArrayList<>();
		List<Player> players2 = new ArrayList<>();
		for (int i = 1; i <= players.length; i++) {
			if (i%2==1)
				players1.add(players[i-1]);
			else
				players2.add(players[i-1]);
		}

		List<String> playerNames = new ArrayList<>();

		List<Player> playingNow1 = getPlayingPlayersForTeam(players1);
		List<Player> playingNow2 = getPlayingPlayersForTeam(players2);

		for (Player player : playingNow1)
			playerNames.add(player.name);
		for (Player player : playingNow2)
			playerNames.add(player.name);

		Collections.sort(playerNames);
		return playerNames;
	}

	private List<Player> getPlayingPlayersForTeam(List<Player> teamPlayers) {
		PriorityQueue<Player> playingQueue = new PriorityQueue<>(teamPlayers.size(), new PlayComparator());
		PriorityQueue<Player> benchQueue = new PriorityQueue<>(11, new BenchComparator());
		Set<Player> playingSet = new HashSet<>();

		// check if we dont need to simulate
		if (playersAllowed == teamPlayers.size())
			return teamPlayers;

		// create the playing set
		for (int i = 0; i < playersAllowed; i++) {
			playingQueue.add(teamPlayers.get(i));
			playingSet.add(teamPlayers.get(i));
		}

		// create the bench
		for (int i = playersAllowed; i < teamPlayers.size(); i++) {
			benchQueue.add(teamPlayers.get(i));
		}

		// now simulate the match
		for (int i = 0; i < totalGameTime; i++) {
			// play -- add a minute to each player.
			for (Player player : playingSet) {
				player.playedTime += 1;
			}

			// exchange the players.
			Player toBench = playingQueue.remove();
			playingSet.remove(toBench);

			Player toPlay = benchQueue.remove();
			playingQueue.add(toPlay);
			playingSet.add(toPlay);

			benchQueue.add(toBench);
		}

		return new ArrayList<>(playingSet);
	}



	// player class

	private class Player implements Comparable<Player>{
		int id;
		int shotPercent;
		int height;
		String name;
		int playedTime = 0;
		int rank;

		public Player(int id, int shotPercent, int height, String name) {
			this.id = id;
			this.shotPercent = shotPercent;
			this.height = height;
			this.name = name;
		}

		@Override
		public int compareTo(Player other) {
			if (this.shotPercent > other.shotPercent)
				return -1;
			else if (this.shotPercent < other.shotPercent)
				return 1;
			else {
				if (this.height > other.height)
					return -1;
				else if (this.height > other.height)
					return 1;

			}

			return 0;
		}

		public boolean equals(Object obj) {
			if (!(obj instanceof Player))
				return false;
			Player other = (Player) obj;

			if (other.id == this.id)
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			return id;
		}
	}

	private class PlayComparator implements Comparator<Player> {

		@Override
		public int compare(Player player, Player other) {
			if (player.playedTime > other.playedTime)
				return -1;
			else if (player.playedTime < other.playedTime)
				return 1;
			else {
				if (player.rank > other.rank)
					return -1;
				else if (player.rank < other.rank)
					return 1;
			}

			return 0;
		}
	}

	private class BenchComparator implements Comparator<Player> {

		@Override
		public int compare(Player player, Player other) {
			if (player.playedTime < other.playedTime)
				return -1;
			else if (player.playedTime > other.playedTime)
				return 1;
			else {
				if (player.rank < other.rank)
					return -1;
				else if (player.rank > other.rank)
					return 1;
			}

			return 0;
		}
	}
}

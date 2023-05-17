package misc.java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8Stuff {

	// example to flatMap a map of <id,position_list> to list of movement(id,position)
	public void flatMapExample() {
		Map<String, List<Position>> idVsPositionList = new HashMap<>();

		List<Movement> movementList = idVsPositionList.entrySet()
				.stream()
				.flatMap(ent -> ent.getValue()
						.stream()
						.map(position -> new Movement(ent.getKey(), position)))
				.collect(Collectors.toList());
	}

	private class Position {

	}

	private class Movement {
		private final String id;
		private final Position position;

		Movement(String id, Position position) {
			this.id = id;
			this.position = position;
		}
	}
}

package basar.data;

import java.util.HashMap;
import java.util.Map;


public class SimplePositionRepository implements PositionRepository {

	private long nextId = 1;
	
	private Map<Long, Position> positions = new HashMap<Long, Position>();
	
	@Override
	public synchronized Position save(Position position) {
		position.setId(nextId++);
		positions.put(nextId, position);
		return position;
	}

	@Override
	public void save(Iterable<Position> positions) {
		for (Position position : positions) {
			save(position);
		}
	}
	
	public Map<Long, Position> getPositions() {
		return positions;
	}

}

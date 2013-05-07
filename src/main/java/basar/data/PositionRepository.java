package basar.data;


public interface PositionRepository {

	Position save(Position position);

	void save(Iterable<Position> positions);

}

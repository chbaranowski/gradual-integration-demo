package basar.core;

import basar.data.Position;

public interface Basar {

	Position createPosition(String basarNumber, Long price, String description);

	void buy(Iterable<Position> positions);

}

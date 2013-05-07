package basar.core;

import javax.inject.Inject;

import basar.data.Position;
import basar.data.PositionRepository;
import basar.data.User;
import basar.data.UserRepository;

public class BasarService implements Basar {

    @Inject
    PositionRepository positionRepository;

    @Inject
	UserRepository userRepository;
	
	@Override
	public Position createPosition(String basarNumber, Long price, String description) {
		User seller = userRepository.findByBasarNumber(basarNumber);
		Position position = new Position();
		position.setDescription(description);
		position.setPrice(price);
		position.setSeller(seller);
		position.setPurchased(false);
		return positionRepository.save(position);
	}

}

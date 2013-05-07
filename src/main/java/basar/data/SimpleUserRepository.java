package basar.data;

import java.util.HashMap;
import java.util.Map;


public class SimpleUserRepository implements UserRepository {

	private Map<String, User> users = new HashMap<String, User>();
	
	public void addUser(User user){
		users.put(user.getBasarNumber(), user);
	}
	
	@Override
	public User findByBasarNumber(String basarNumber) {
		return users.get(basarNumber);
	}

}

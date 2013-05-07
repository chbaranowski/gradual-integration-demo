package basar.data;

import java.util.List;


public class User {

	private Long id;

	private String basarNumber;

	private String name;

	private String email;

	private String lastname;

	private List<Position> positions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBasarNumber() {
		return basarNumber;
	}

	public void setBasarNumber(String basarNumber) {
		this.basarNumber = basarNumber;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", basarNumber='" + basarNumber + '\''
				+ ", name='" + name + '\'' + ", email='" + email + '\''
				+ ", lastname='" + lastname + '\'' + '}';
	}
}

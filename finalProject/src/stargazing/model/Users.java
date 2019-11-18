package stargazing.model;
public class Users extends Persons{
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;
	protected String street;
	protected String city;
	protected String state;
	protected String zip;
	protected UserLevel userLevel;
	public enum UserLevel{
		active, normal, lazy
	}

	public Users(int userId) {
		super(userId);
	}
	public Users(String userName){
		super(userName);
	}



	public Users(int userId, String userName, String password, String firstName, String lastName, String email,
			String phone, String street, String city, String state, String zip, UserLevel userLevel) {
		super(userId, userName, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.userLevel = userLevel;
	}

	public Users(String userName, String password, String firstName, String lastName, String email, String phone,
			String street, String city, String state, String zip, UserLevel userLevel) {
		super(userName, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.userLevel = userLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	
	
	
}

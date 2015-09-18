package cloud.mcar.model;

public class Credential {
	private int id;
	private String username;
	private String password;
	public Credential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Credential(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	
}

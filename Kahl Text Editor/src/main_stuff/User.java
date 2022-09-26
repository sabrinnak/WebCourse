package main_stuff;

public class User implements Comparable<User> {
	private String name;
	private String password;

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public static String getName(User user) {
		return user.name;
	}

	@Override
	public int compareTo(User o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
}

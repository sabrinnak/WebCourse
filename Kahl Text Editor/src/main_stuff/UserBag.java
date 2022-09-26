package main_stuff;

import java.util.TreeMap;

public class UserBag {
	private static TreeMap<String, User> theMap = new TreeMap<String, User>();

	public UserBag() {
		//created so that the map will not be empty
		User usr = new User("abcdef", "Pwd123");
		theMap.put(User.getName(usr), usr);
	}

	public void insert(User user) {
		theMap.put(User.getName(user), user);
	}

	public static boolean find(String key) {
		if (theMap.equals(null)) {
			return false;
		} else if (theMap.containsKey(key)) {
			return true;
		} else {
			return false;
		}
	}
}

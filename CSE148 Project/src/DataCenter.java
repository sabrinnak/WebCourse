import java.util.ArrayList;

public class DataCenter {
	
	private ArrayList<User> userList = new ArrayList<>();
	

	private static DataCenter center = null;
	
	
	private DataCenter() {
		User usr1 = new User("usr123", "pwd123");
		userList.add(usr1);
		
	}
	
	public static DataCenter getInstance() {
		
		if (center == null) {
			center = new DataCenter();
		}
		return center;
	}
	
	public void addUser(User usr) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i) != usr) {
				userList.add(usr);
			}
		}

	}
	
	public boolean validateUserCredential(String usr, String pwd) {
		boolean validated = false;
		for (User user : userList) {
			if (user.validate(usr, pwd)) {
				validated = true;
				break;
			}
		}
		return validated;
	}
	
}

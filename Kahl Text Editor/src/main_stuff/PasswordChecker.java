package main_stuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

	public static boolean pass(String password) {
		boolean ok = false;
		String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$";

		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(password);

		while (m.find()) {
			ok = true;
		}

		return ok;
	}
}

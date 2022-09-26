package main_stuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FleschScore {

	public static String getFleschScore(String str) {
		String score = "";
		double re = 0;
		double basis = 206.835;
		double asl = 0;
		double asw = 0;
		double syllables = 0;

		double num = countWords(str) / countSentences(str);
		asl = 1.015 * num;

		String[] stri = str.split(" ");
		for (int i = 0; i < stri.length; i++) {
			syllables = syllables + countSyllables(stri[i]);
		}

		double numb = syllables / countWords(str);
		asw = 84.6 * numb;

		re = basis - asl;
		re = re - asw;

		if (re <= 100 && re >= 90) {
			score = "Very Easy";
		} else if (re <= 89 && re >= 80) {
			score = "Easy";
		} else if (re <= 79 && re >= 70) {
			score = "Fairly Easy";
		} else if (re <= 69 && re >= 60) {
			score = "Standard";
		} else if (re <= 59 && re >= 50) {
			score = "Fairly Difficult";
		} else if (re <= 49 && re >= 30) {
			score = "Difficult";
		} else if (re <= 29 && re >= 0) {
			score = "Very Confusing";
		}

		return score;

	}

	public static double countSyllables(String word) {
		double number = 0;

		String pattern = "[AEIOUYaeiouy]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(word);

		String lastToken = "";
		while (m.find()) {
			number++;
			lastToken = m.group(); // give last token
		}
		if (lastToken.equals("e") && number > 1 && word.charAt(word.length() - 1) == 'e') {
			number--;
		}

		return number;

	}

	public static double countWords(String str) {
		double number = 0;

		String pattern = "[A-Za-z]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);

		while (m.find()) {
			number++;
		}

		return number;
	}

	public static double countSentences(String str) {
		double number = 0;

		String pattern = "[^.?!]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);

		while (m.find()) {
			number++;
		}

		return number;

	}

}

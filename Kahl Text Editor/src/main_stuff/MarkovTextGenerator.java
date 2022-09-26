package main_stuff;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Random;

public class MarkovTextGenerator {

	public static Hashtable<String, Vector<String>> markovTable = new Hashtable<String, Vector<String>>();
	static Random rnd = new Random();

	/*
	 * Add words
	 */
	public static void addWords(String phrase, String start) {
		markovTable.put("_start", new Vector<String>());
		markovTable.put("_end", new Vector<String>());
		// put each word into an array
		String[] words = phrase.split(" ");

		// Loop through each word, check if it's already added
		// if its added, then get the suffix vector and add the word
		// if it hasn't been added then add the word to the list
		// if its the first or last word then select the _start / _end key

		for (int i = 0; i < words.length; i++) {

			// Add the start and end words to their own
			if (i == 0) {
				Vector<String> startWords = markovTable.get("_start");
				startWords.add(start);

				Vector<String> suffix = markovTable.get(start);
				if (suffix == null) {
					suffix = new Vector<String>();
					suffix.add(words[phrase.indexOf(start)]);
					markovTable.put(start, suffix);
				}

			} else if (i == words.length - 1) {
				Vector<String> endWords = markovTable.get("_end");
				endWords.add(words[i]);

			} else {
				Vector<String> suffix = markovTable.get(words[i]);
				if (suffix == null) {
					suffix = new Vector<String>();
					suffix.add(words[i + 1]);
					markovTable.put(words[i], suffix);
				} else {
					suffix.add(words[i + 1]);
					markovTable.put(words[i], suffix);
				}
			}
		}
	}

	/*
	 * Generate a markov phrase
	 */
	public static String generateSentence(int size) {

		// Vector to hold the phrase
		Vector<String> newPhrase = new Vector<String>();

		// String for the next word
		String nextWord = "";

		// Select the first word
		Vector<String> startWords = markovTable.get("_start");
		int startWordsLen = startWords.size();
		nextWord = startWords.get(rnd.nextInt(startWordsLen));
		newPhrase.add(nextWord);

		// Keep looping through the words until we've reached the end
		for (int i = 1; i < size; i++) {
			Vector<String> wordSelection = markovTable.get(nextWord);
			int wordSelectionLen = wordSelection.size();
			nextWord = wordSelection.get(rnd.nextInt(wordSelectionLen));
			newPhrase.add(nextWord);
		}

		return newPhrase.toString();
	}
}

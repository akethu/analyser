import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Analyser {
	/* Can be cutomised */
	// To add keywords, simply add strings inside the brackets of asList
	// If I want to add "person" to the keyword list, I simply append as follows: Arrays.asList(..., "person");
	static final List<String> keywords = Arrays.asList("participant", "participants", "male", "female", "university", "old", "young", "students", "youth", "accessibility", "usability", "observation", "subjects", "subject", "group", "groups", "college", "adults", "adult", "elderly", "individual", "individuals", "men", "man", "women", "woman");
	
	static ArrayList<String> acc = new ArrayList<String>();
	static ArrayList<String> tracker = new ArrayList<String>();
	static ArrayList<Integer> index = new ArrayList<Integer>();
	static boolean has = false;
	static String theWord = "";
	static String stringed = "";
	static int num = 0;
	
	public void stringMaker() {
		for(int i = 0; i < tracker.size(); i++) {
			System.out.println("Contains " + tracker.get(i) + ":");
			
			/* Can be customised */
			// Change the 2 below to the number of sentences you want to read, before the sentence containing the current keyword
			// Eg: If you change the 2 (you need to change both the 2's below) to 3, then you will print 3 sentences (if exists) before the sentence containing the keyword
			int prev = (index.get(i) >= 2) ? 2 : index.get(i);
			
			while(prev >= 0) {
				System.out.println(acc.get(index.get(i) - prev));
				prev--;
			}
			
			/* Can be customised */
			// Change the 2 below to the number of sentences you want to read, after the sentence containing the current keyword
			// Eg: If you change the 2 (you need to change both the 2's below) to 3, then you will print 3 sentences (if exists) after the sentence containing the keyword
			int suc = ((acc.size() - index.get(i)) >= 2) ? 2 : (acc.size() - index.get(i));
			
			while(suc > 0) {
				System.out.println(acc.get(index.get(i) + suc));
				suc--;
			}
			System.out.print("\n");
		}
	}

	public void tokenizer(String st) {
		StringTokenizer token = new StringTokenizer(st);
		while(token.hasMoreTokens()) {
			String s = token.nextToken();
			stringed += s + " ";
			if(keywords.contains(s.toLowerCase())) {
				has = true;
				theWord = s;
			}
			if(s.equals(".") || s.charAt(s.length() - 1) == '.') {
				acc.add(stringed);
				if(has) {
					index.add(num);
					has = false;
					tracker.add(theWord);
				}
				stringed = "";
				num++;
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		/* To be customised */
		// Change the path of the file below to the path you have your .txt file on
		File file = new File("/Users/adithyakethu/Desktop/viz.txt");
		Scanner scnr = new Scanner(file);
		Analyser obj = new Analyser();
		
		while(scnr.hasNextLine()) {
			obj.tokenizer(scnr.nextLine());
		}
		obj.stringMaker();
		if(num == 0) {
			System.out.println("Oops! Nothing relevant found.");
		}
	}

}
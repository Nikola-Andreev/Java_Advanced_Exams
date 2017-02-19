package R_MyExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem02 {
    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, String> wordsForReplace = new LinkedHashMap<>();

        while (true) {
            String input = scan.readLine();
            if ("Ascend".equals(input)) break;
            for (String s : wordsForReplace.keySet()) {
                input = input.replaceAll(s, wordsForReplace.get(s));
            }
            Matcher matcher = Pattern.compile("(_|,)(?<word>[a-zA-Z]+)(?<number>[\\d])").matcher(input);
            while (matcher.find()){
                String allMatch = matcher.group(0);
                String word = matcher.group("word");
                int number = Integer.valueOf(matcher.group("number"));
                StringBuilder newWord = new StringBuilder();
                if (matcher.group(0).charAt(0) == '_'){
                    for (int i = 0; i < word.length(); i++) {
                        newWord.append((char) (word.charAt(i) - number));
                    }
                } else {
                    for (int i = 0; i < word.length(); i++) {
                        newWord.append((char) (word.charAt(i) + number));
                    }
                }

                input = input.replaceAll(allMatch, newWord.toString());
                wordsForReplace.put(allMatch, newWord.toString());
            }
            System.out.println(input);
        }
    }
}

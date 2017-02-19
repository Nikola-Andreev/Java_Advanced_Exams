package R_MyExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem04_Variant2 {

    private static LinkedHashMap<String, StringBuilder> battles = new LinkedHashMap<>();
    private static LinkedHashMap<String, Long> results = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = scan.readLine().split(" ");
            if ("osu!".equals(input[0])) break;
            Long player1Score = Long.valueOf(input[0]);
            String player1 = input[1].split("<->")[0];
            String player2 = input[1].split("<->")[1];
            if (player1.equals(player2)) continue;
            Long player2Score = Long.valueOf(input[2]);
            Long player1Diff = player1Score - player2Score;
            Long player2Diff = player2Score - player1Score;

            processPlayers(player1, player2, player1Diff);
            processPlayers(player2, player1, player2Diff);
        }

        results.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue))
                .forEach(player -> {
            System.out.println(player.getKey() + " - " + "(" + player.getValue()+ ")");
            System.out.println(battles.get(player.getKey()));
        });
    }

    private static void processPlayers(String player1, String player2, Long playerDiff) {
        if (!results.containsKey(player1)) {
            results.put(player1, 0L);
        }
        if (!results.containsKey(player2)) {
            results.put(player2, 0L);
        }
        results.put(player1, results.get(player1) + playerDiff);
        results.put(player2, results.get(player2) + playerDiff);

        if (!battles.containsKey(player1)){
            battles.put(player1, new StringBuilder());
        }
        if (!battles.containsKey(player2)){
            battles.put(player2, new StringBuilder());
        }
        battles.get(player1).append("*   ").append(player2).append(" <-> ").append(playerDiff).append("\n");
        battles.get(player2).append("*   ").append(player1).append(" <-> ").append(playerDiff).append("\n");
    }
}
package R_MyExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class B_Earthquake02 {

    private static LinkedHashMap<String, LinkedHashMap<String, Long>> result = new LinkedHashMap<>();
    private static int counter = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        while (true){
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

        result.entrySet().stream().sorted((p1, p2) -> {
            long sum1 = p1.getValue().values().stream().mapToLong(l -> l).sum();
            long sum2 = p2.getValue().values().stream().mapToLong(l -> l).sum();
            return Long.compare(sum2, sum1);
        }).forEach(player -> {
            System.out.println(player.getKey() + " - " + "(" + player.getValue().values().stream().mapToLong(l -> l).sum() +")") ;
            player.getValue().entrySet().forEach(opponent -> System.out.println("*   " + opponent.getKey().trim() + " <-> " + opponent.getValue()));
        });
    }

    private static void processPlayers(String player1, String player2, Long playerDiff) {
        if (result.containsKey(player1)){
            if (result.get(player1).containsKey(player2)){
                result.get(player1).put(player2 + new String(new char[counter]).replace("\0", " "), playerDiff);
                counter++;
            } else {
                result.get(player1).put(player2, playerDiff);
            }
        } else {
            LinkedHashMap<String, Long> temp = new LinkedHashMap<>();
            temp.put(player2, playerDiff);
            result.put(player1, temp);
        }
    }
}
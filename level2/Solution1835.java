package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1835 {
    static List<String> orderList;
    static Map<String, Integer> board;
    static String[] people;

    public int solution(int n, String[] data) {
        orderList = new ArrayList<>();
        board = new HashMap<>();
        people = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
        int answer = 0;
        for (String p : people) {
            board.put(p, 1);
        }

        generateOrder("", data);

        return orderList.size();
    }

    public void generateOrder(String p, String[] data) {
        if (p.length() == 8) {
            if (validate(p, data)) {
                orderList.add(p);
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (board.get(people[i]) > 0) {
                board.put(people[i],
                        board.get(people[i]) - 1);
                generateOrder(p + people[i], data);
                board.put(people[i],
                        board.get(people[i]) + 1);
            }
        }
    }

    public static boolean validate(String target, String[] data) {

        for (int i = 0; i < data.length; i++) {
            char pos1 = data[i].charAt(0);
            char pos2 = data[i].charAt(2);

            char oper = data[i].charAt(3);
            int cnt = data[i].charAt(4) - '0';

            if (oper == '=') {
                if (Math.abs(target.indexOf(pos1) - target.indexOf(pos2)) != cnt + 1) {
                    return false;
                }
            } else if (oper == '>') {
                if (Math.abs(target.indexOf(pos1) - target.indexOf(pos2)) <= cnt + 1) {
                    return false;
                }
            } else {
                if (Math.abs(target.indexOf(pos1) - target.indexOf(pos2)) >= cnt + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1835().solution(1, new String[]{"N~F=0", "R~T>2"}));
    }
}
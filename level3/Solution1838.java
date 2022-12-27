package level3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1838 {
    public static int solution(int n, int m, int[][] timetable) {
        final int MAX = 1320;

        int answer = 0;
        int count = 0;
        boolean canPush = true;

        int[] time = new int[MAX + 1];

        Set<int[]> set;
        Arrays.sort(timetable, (o1, o2) -> o1[0] > o2[0] ? 1 : -1);

        for (int i = 0; i < timetable.length; i++) {
            for (int j = timetable[i][0]; j <= timetable[i][1]; j++) {
                time[j]++;
                count = Math.max(count, time[j]);
            }
        }

        if (count == 1) {
            return 0;
        }

        for (int maxDistance = 2 * n - 2; maxDistance > 0; maxDistance--) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    set = new HashSet<>();
                    set.add(new int[]{r, c});

                    for (int x = r; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (x == r & y <= c) {
                                continue;
                            }

                            canPush = true;
                            int[] now = {x, y};
                            for (int[] s : set) {
                                if (getDistance(s, now) < maxDistance) {
                                    canPush = false;
                                    break;
                                }
                            }

                            if (canPush) {
                                set.add(now);
                                if (set.size() == count) {
                                    return maxDistance;
                                }
                            }

                        }
                    }
                }
            }
        }
        return answer;
    }

    private static int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 2, new int[][]{{1170, 1210}, {1200, 1260}}) == 4);
        System.out.println(solution(2, 1, new int[][]{{840, 900}}) == 0);
        System.out.println(solution(2, 2, new int[][]{{600, 630}, {630, 700}}) == 2);
        System.out.println(solution(4, 5, new int[][]{{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}}) == 4);
    }
}

package com.huawei.rico.poc;

import java.util.*;
import java.util.stream.Collectors;

public class EarthquakeAlarm {
    static class Earthquake {
        public final int radius;
        public final int rowIdx;
        public final int colIdx;

        Earthquake(int radius, int rowIdx, int colIdx) {
            this.radius = radius;
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    private static int[] getNotificationOrder(int mapSideLen, int  gridSideLen, Earthquake earthquake, int[][] userArray){
        int num = mapSideLen / gridSideLen;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] ints : userArray) {
            int x_num = ints[0];
            int y_num = ints[1];
            int grid_num = (x_num / gridSideLen) * num + y_num / gridSideLen + 1;
            map.put(grid_num, map.getOrDefault(grid_num, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (int key : map.keySet()) {
            int grid_x = ((key - 1) / num) * gridSideLen + gridSideLen / 2 ;
            int grid_y = (key - 1) % num * gridSideLen + gridSideLen / 2;
            int dis = Math.abs(earthquake.rowIdx - grid_x) + Math.abs(earthquake.colIdx - grid_y);
            if (dis <= earthquake.radius) {
                list.add(new int[]{dis, map.get(key), key});
            }
        }

        if (list.size() == 0) {
            return new int[]{};
        }

        list.sort((o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                if (o1[1] > o2[1]) {
                    return -1;
                } else if (o1[1] < o2[1]) {
                    return 1;
                } else {
                    return Integer.compare(o1[2], o2[2]);
                }
            }
        });

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i)[2];
        }

        return res;
    }

    public static void Simulate( int radius, int yValue, int xValue, int[][] userArray, int squareSide, int gridSide){
        System.out.println("begin to simulate");
        Earthquake earthquake = new Earthquake(radius, yValue, xValue);

        int[] notify = getNotificationOrder(squareSide, gridSide, earthquake, userArray);
        if (notify.length == 0) {
            System.out.print("[]");
        } else {
            System.out.print(Arrays.stream(notify).mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ", "[", "]")));
        }
        System.out.println("end of simulate.");
    }
}

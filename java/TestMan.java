package com.huawei.rico.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class DemoNow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String[] lineSplit = br.readLine().split(" ");

        int squareSide = Integer.parseInt(lineSplit[0]); // 地图边长

        int gridSide = Integer.parseInt(lineSplit[1]); // 删格边长

        int radius = Integer.parseInt(lineSplit[2]); // 通知半径

        lineSplit = br.readLine().split(" ");

        int yValue = Integer.parseInt(lineSplit[0]); // 地震坐标y值

        int xValue = Integer.parseInt(lineSplit[1]); // 地震坐标x值

        int userCount = Integer.parseInt(br.readLine()); // 用户总数

        int[][] userArray = new int[userCount][];

        for (int idx = 0; idx < userArray.length; idx++) {

            lineSplit = br.readLine().split(" ");

            userArray[idx] = new int[2];

            userArray[idx][0] = Integer.parseInt(lineSplit[0]);

            userArray[idx][1] = Integer.parseInt(lineSplit[1]);

        }
        System.out.println( userArray.toString() );
        br.close();
        System.out.println("ready to run earthquake alarming!");
        EarthquakeAlarm.Simulate( radius, yValue, xValue, userArray, squareSide, gridSide );
        System.exit(1);
    }
}

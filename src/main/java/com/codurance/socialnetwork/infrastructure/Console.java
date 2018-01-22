package com.codurance.socialnetwork.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    public void printLine(String output) {
        System.out.println(output);
    }

    public String readLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            return  bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}

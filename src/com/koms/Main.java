package com.koms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args)  throws InterruptedException, IOException {
        System.out.println("Hallo");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (System.in.available() > 0)
                if (br.readLine().equals("exit"))
                    break;
        }
        System.out.println("Stopping");
    }
}

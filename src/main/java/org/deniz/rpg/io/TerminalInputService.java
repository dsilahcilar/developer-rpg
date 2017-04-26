package org.deniz.rpg.io;

import java.util.Scanner;

public class TerminalInputService implements InputService {
    private final Scanner reader = new Scanner(System.in);

    public int readInt(String text) {
        System.out.print(text);
        return reader.nextInt();
    }

    public String readStr(String text) {
        System.out.print(text);
        return reader.nextLine();
    }
}

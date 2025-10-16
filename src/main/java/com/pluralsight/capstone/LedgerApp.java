package com.pluralsight.capstone;

import java.io.IOException;

public class LedgerApp extends Screen {
    public static void main(String[] args) throws IOException, InterruptedException {
        // this initializes the screen class, the main display of the CLI
        Screen screen = new Screen();

        String message = """
                
                
                
                 ██▓    ▄▄▄       █    ██  ███▄    █  ▄████▄   ██░ ██  ██▓ ███▄    █   ▄████\s
                ▓██▒   ▒████▄     ██  ▓██▒ ██ ▀█   █ ▒██▀ ▀█  ▓██░ ██▒▓██▒ ██ ▀█   █  ██▒ ▀█▒
                ▒██░   ▒██  ▀█▄  ▓██  ▒██░▓██  ▀█ ██▒▒▓█    ▄ ▒██▀▀██░▒██▒▓██  ▀█ ██▒▒██░▄▄▄░
                ▒██░   ░██▄▄▄▄██ ▓▓█  ░██░▓██▒  ▐▌██▒▒▓▓▄ ▄██▒░▓█ ░██ ░██░▓██▒  ▐▌██▒░▓█  ██▓
                ░██████▒▓█   ▓██▒▒▒█████▓ ▒██░   ▓██░▒ ▓███▀ ░░▓█▒░██▓░██░▒██░   ▓██░░▒▓███▀▒
                ░ ▒░▓  ░▒▒   ▓▒█░░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒ ░ ░▒ ▒  ░ ▒ ░░▒░▒░▓  ░ ▒░   ▒ ▒  ░▒   ▒\s
                ░ ░ ▒  ░ ▒   ▒▒ ░░░▒░ ░ ░ ░ ░░   ░ ▒░  ░  ▒    ▒ ░▒░ ░ ▒ ░░ ░░   ░ ▒░  ░   ░\s
                  ░ ░    ░   ▒    ░░░ ░ ░    ░   ░ ░ ░         ░  ░░ ░ ▒ ░   ░   ░ ░ ░ ░   ░\s
                    ░  ░     ░  ░   ░              ░ ░ ░       ░  ░  ░ ░           ░       ░\s
                                                     ░                                      \s
                ▓█████▄  ▒█████   █    ██   ▄████  ██░ ██   █████▒██▓     ▒█████   █     █░ \s
                ▒██▀ ██▌▒██▒  ██▒ ██  ▓██▒ ██▒ ▀█▒▓██░ ██▒▓██   ▒▓██▒    ▒██▒  ██▒▓█░ █ ░█░ \s
                ░██   █▌▒██░  ██▒▓██  ▒██░▒██░▄▄▄░▒██▀▀██░▒████ ░▒██░    ▒██░  ██▒▒█░ █ ░█  \s
                ░▓█▄   ▌▒██   ██░▓▓█  ░██░░▓█  ██▓░▓█ ░██ ░▓█▒  ░▒██░    ▒██   ██░░█░ █ ░█  \s
                ░▒████▓ ░ ████▓▒░▒▒█████▓ ░▒▓███▀▒░▓█▒░██▓░▒█░   ░██████▒░ ████▓▒░░░██▒██▓  \s
                 ▒▒▓  ▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒  ░▒   ▒  ▒ ░░▒░▒ ▒ ░   ░ ▒░▓  ░░ ▒░▒░▒░ ░ ▓░▒ ▒   \s
                 ░ ▒  ▒   ░ ▒ ▒░ ░░▒░ ░ ░   ░   ░  ▒ ░▒░ ░ ░     ░ ░ ▒  ░  ░ ▒ ▒░   ▒ ░ ░   \s
                 ░ ░  ░ ░ ░ ░ ▒   ░░░ ░ ░ ░ ░   ░  ░  ░░ ░ ░ ░     ░ ░   ░ ░ ░ ▒    ░   ░   \s
                   ░        ░ ░     ░           ░  ░  ░  ░           ░  ░    ░ ░      ░     \s
                 ░                                                                          \s
                
                
                
                """;

        // print out the message with a brief delay
        System.out.println(message);
        Thread.sleep(5000);

        // this uses the screen class to load a homescreen
        screen.homeScreen();

    }
}

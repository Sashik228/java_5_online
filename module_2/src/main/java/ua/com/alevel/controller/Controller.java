package ua.com.alevel.controller;

import ua.com.alevel.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.Color.Colors;
import static ua.com.alevel.util.Color.Colors.*;

public class Controller {

    public static int departurePoint;
    public static int destination;

    private final Service Service = new Service();
    Colors color = new Colors();

    public void startApp() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("     _____________________________________________________ ");
        System.out.println(PURPLE + "    |Hallo, We greet you in <goTrip> app ;)               |");
        System.out.println("    |We help you to calculate the best route for your trip|");
        String select;
        menu(color);
        while((select = bf.readLine()) != null) {
            cases(bf, select);
        }
    }

    private void menu(Colors ignoredColor) {
        System.out.println(WHITE + "    |-----------------------------------------------------|");
        System.out.println(YELLOW + "    BUILD the route                                 >>>   1");
        System.out.println("    Calculate route                                 >>>   2");
        System.out.println(WHITE + "    Clean information from your computer            >>>   3");
        System.out.println("    |.....................................................|");
        System.out.println(RED + "    EXIT                                            >>>   0");
        System.out.println(WHITE + "    |.....................................................|");
        System.out.println("Information for planing trip have been sent to your e-mail, check your box, please...");
        System.out.println("File_PATH: ../java_5_online/module_2/input.txt" + WHITE);
    }

    private void cases(BufferedReader reader, String select) throws IOException {
        switch(select) {
            case "1" -> build(reader);
            case "2" -> calculate(reader);
            case "3" -> clean(reader);
            case "0" -> System.exit(0);
        }
        menu(color);
    }

    private void build(BufferedReader reader) throws IOException {
        System.out.println("Please, enter № of Departure point of your trip (according our offer in java_5_online/module_2/input.txt:");
        String dep = reader.readLine();
        System.out.println("Please, enter № of Destination of your trip (according our offer in java_5_online/module_2/input.txt:");
        String dest = reader.readLine();
        departurePoint = Integer.parseInt(dep);
        destination = Integer.parseInt(dest);
        System.out.println(GREEN + "The route was built, you can go to next step: " + WHITE);
    }

    private void calculate(BufferedReader ignoredReader) throws IOException {
        Service.matrix();
    }

    private void clean(BufferedReader ignoredReader) {
        Service.deleteOutputFile();
    }
}
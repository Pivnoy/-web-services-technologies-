package org.example;

import command.ifc.Command;
import command.impl.FindCommand;
import command.impl.RegisterCommand;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDIInquiryPortType;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        UDDIClient uddiClient = new UDDIClient("/Users/eymakarev/IdeaProjects/web-services-technologies/lab7/juddi/src/main/resources/META-INF/uddi.xml");
        UDDIClerk clerk = uddiClient.getClerk("default");
        Transport transport = uddiClient.getTransport("default");
        UDDIInquiryPortType querySrv = transport.getUDDIInquiryService();
        if (clerk == null)
            throw new Exception("the clerk wasn't found, check the config file!");

        HashMap<String, Command> commands = new HashMap<String, Command>() {{
            put("register", new RegisterCommand(clerk));
            put("find", new FindCommand(clerk, querySrv));
        }};

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите команду: ");
                String command = scanner.nextLine().toLowerCase().trim();
                if (!commands.containsKey(command)) {
                    System.out.println("Команда не найдена");
                } else {
                    commands.get(command).execute(scanner);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }
    }
}
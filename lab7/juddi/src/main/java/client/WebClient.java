package client;

import client.command.impl.CreateCommand;
import client.command.impl.DeleteCommand;
import client.command.impl.ListCommand;
import client.command.impl.UpdateCommand;
import command.ifc.Command;
import org.example.standalone.service.ClusterVmService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class WebClient {

    public static void runClient(String serviceURL, Scanner scanner) throws MalformedURLException {
        URL url = new URL(serviceURL);
        ClusterVmService service = new ClusterVmService(url);

        HashMap<String, Command> commands = new HashMap<String, Command>() {{
            put("create", new CreateCommand(service));
            put("delete", new DeleteCommand(service));
            put("update", new UpdateCommand(service));
            put("list", new ListCommand(service));
        }};

        System.out.println("Запущен сервис по управлению виртуальными машинами");
        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine().toLowerCase().trim();
            if (command.equals("exit")) {
                return;
            } else if (commands.containsKey(command)) {
                commands.get(command).execute(scanner);
            } else {
                System.out.println("Команда не найдена");
            }
        }
    }
}
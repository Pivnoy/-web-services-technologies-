import client.RequestSender;
import command.ifc.Command;
import command.impl.CreateCommand;
import command.impl.DeleteCommand;
import command.impl.ListCommand;
import command.impl.UpdateCommand;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static String baseURL = "http://localhost:8080/v1/vms";

    public static void main(String[] args) throws MalformedURLException {
        RequestSender sender = new RequestSender();

        HashMap<String, Command> commands = new HashMap<String, Command>() {{
            put("create", new CreateCommand(sender));
            put("delete", new DeleteCommand(sender));
            put("update", new UpdateCommand(sender));
            put("list", new ListCommand(sender));
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
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }
    }
}
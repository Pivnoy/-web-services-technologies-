import command.ifc.Command;
import command.impl.CreateCommand;
import command.impl.DeleteCommand;
import command.impl.ListCommand;
import command.impl.UpdateCommand;
import org.example.standalone.service.ClusterVmService;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9000/ClusterVmService?wsdl");
        ClusterVmService service = new ClusterVmService(url);

        HashMap<String, Command> commands = new HashMap<>() {{
            put("create", new CreateCommand(service));
            put("delete", new DeleteCommand(service));
            put("update", new UpdateCommand(service));
            put("list", new ListCommand(service));
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
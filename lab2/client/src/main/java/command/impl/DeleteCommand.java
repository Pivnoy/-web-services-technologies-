package command.impl;

import command.ifc.Command;
import lombok.AllArgsConstructor;
import org.example.standalone.service.ClusterVmService;
import org.example.standalone.service.QueryStatus;

import java.util.Scanner;
import java.util.UUID;

@AllArgsConstructor
public class DeleteCommand implements Command {

    private ClusterVmService clusterVmService;

    @Override
    public void execute(Scanner scanner) {
        UUID id = null;
        System.out.print("Введите id виртуальное машины: ");
        try {
            String idInput = scanner.nextLine();
            if (idInput != null && !idInput.isEmpty()) {
                id = UUID.fromString(idInput);
            }

            QueryStatus status = clusterVmService.getClusterVmWebServicePort().deleteClusterVm(id.toString());

            System.out.println("Получен результат: " + status.value());
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось получить id " + e.getMessage());
        }

    }
}

package command.impl;

import command.ifc.Command;
import lombok.AllArgsConstructor;
import org.example.standalone.service.*;

import java.util.Scanner;
import java.util.UUID;

@AllArgsConstructor
public class UpdateCommand implements Command {

    private ClusterVmService clusterVmService;

    @Override
    public void execute(Scanner scanner) {
        UUID id = null;
        Long vmid = null;
        String name = null, image = null;
        Integer cpu = null, memory = null;

        try {
            System.out.print("Введите id: ");
            String idInput = scanner.nextLine();
            if (idInput != null && !idInput.isEmpty()) {
                id = UUID.fromString(idInput);
            } else {
                System.out.println("id является обязательным параметром!");
                return;
            }

            System.out.print("Введите vmid: ");
            String vmidInput = scanner.nextLine();
            if (vmidInput != null && !vmidInput.isEmpty()) {
                vmid = Long.parseLong(vmidInput);
            }

            System.out.print("Введите name: ");
            String nameInput = scanner.nextLine();
            if (nameInput != null && !nameInput.isEmpty()) {
                name = nameInput;
            }

            System.out.print("Введите image: ");
            String imageInput = scanner.nextLine();
            if (imageInput != null && !imageInput.isEmpty()) {
                image = imageInput;
            }

            System.out.print("Введите cpu: ");
            String cpuInput = scanner.nextLine();
            if (cpuInput != null && !cpuInput.isEmpty()) {
                cpu = Integer.parseInt(cpuInput);
            }

            System.out.print("Введите memory: ");
            String memoryInput = scanner.nextLine();
            if (memoryInput != null && !memoryInput.isEmpty()) {
                memory = Integer.parseInt(memoryInput);
            }

            QueryStatus status = this.clusterVmService.getClusterVmWebServicePort().updateClusterVm(id.toString(), vmid, name, image, cpu, memory);

            System.out.println("Получен результат: " + status.value());
        } catch (ValidationError e) {
            System.out.println(e.getFaultInfo().getField() + ": невалидное значение");
        } catch (NotFoundError e) {
            System.out.println(e.getFaultInfo().getEntity() + ": строки с таким значением не существует");
        } catch (DuplicateError e) {
            System.out.println(e.getFaultInfo().getField() + ": уже существует запись с таким значением");
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось получить id " + e.getMessage());
        }
    }
}

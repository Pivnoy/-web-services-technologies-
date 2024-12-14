package client.command.impl;

import command.ifc.Command;
import lombok.AllArgsConstructor;
import org.example.standalone.service.ClusterVmService;
import org.example.standalone.service.DuplicateError;
import org.example.standalone.service.ValidationError;

import java.util.Scanner;

@AllArgsConstructor
public class CreateCommand implements Command {

    private ClusterVmService clusterVmService;

    @Override
    public void execute(Scanner scanner) {
        Long vmid = null;
        String name = null, image = null;
        Integer cpu = null, memory = null;

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

            try {
                String id = this.clusterVmService.getClusterVmWebServicePort().createClusterVm(vmid, name, image, cpu, memory);
                System.out.println("Получен результат: " + id);
            } catch (ValidationError e) {
                System.out.println(e.getFaultInfo().getField() + ": невалидное значение");
            } catch (DuplicateError e) {
                System.out.println(e.getFaultInfo().getField() + ": уже существует запись с таким значением");
            }
    }
}

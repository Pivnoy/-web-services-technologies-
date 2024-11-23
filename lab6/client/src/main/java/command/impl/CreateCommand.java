package command.impl;

import client.RequestSender;
import client.RequestType;
import command.ifc.Command;
import dto.ClusterVm;
import lombok.AllArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.util.Scanner;

@AllArgsConstructor
public class CreateCommand implements Command {

   private RequestSender requestSender;

    @Override
    public void execute(Scanner scanner) throws IOException {
        ClusterVm clusterVm = new ClusterVm();

            System.out.print("Введите vmid: ");
            String vmidInput = scanner.nextLine();
            if (vmidInput != null && !vmidInput.isEmpty()) {
                clusterVm.setVmid(Long.parseLong(vmidInput));
            }

            System.out.print("Введите name: ");
            String nameInput = scanner.nextLine();
            if (nameInput != null && !nameInput.isEmpty()) {
               clusterVm.setName(nameInput);
            }

            System.out.print("Введите image: ");
            String imageInput = scanner.nextLine();
            if (imageInput != null && !imageInput.isEmpty()) {
                clusterVm.setImage(imageInput);
            }

            System.out.print("Введите cpu: ");
            String cpuInput = scanner.nextLine();
            if (cpuInput != null && !cpuInput.isEmpty()) {
                clusterVm.setCpu(Integer.parseInt(cpuInput));
            }

            System.out.print("Введите memory: ");
            String memoryInput = scanner.nextLine();
            if (memoryInput != null && !memoryInput.isEmpty()) {
                clusterVm.setMemory(Integer.parseInt(memoryInput));
            }

            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String id = this.requestSender.sendMutateRequest("", RequestType.POST, ow.writeValueAsString(clusterVm));

                System.out.println("Получен результат: " + id);
            } catch (IllegalStateException e) {
                System.out.println("Получена ошибка\n" + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Значение должно быть целым числом: " + e.getMessage());
            }
    }
}

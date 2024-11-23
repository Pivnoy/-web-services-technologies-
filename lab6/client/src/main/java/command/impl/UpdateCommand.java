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
import java.util.UUID;

@AllArgsConstructor
public class UpdateCommand implements Command {

    private RequestSender requestSender;

    @Override
    public void execute(Scanner scanner) throws IOException {
        ClusterVm clusterVm = new ClusterVm();

        try {
            System.out.print("Введите id: ");
            String idInput = scanner.nextLine();
            if (idInput != null && !idInput.isEmpty()) {
                clusterVm.setId(UUID.fromString(idInput));
            } else {
                System.out.println("id является обязательным параметром!");
                return;
            }

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

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String status = this.requestSender.sendMutateRequest("", RequestType.PUT, ow.writeValueAsString(clusterVm));

            System.out.println("Получен результат: " + status);
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось получить id " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Получена ошибка\n" + e.getMessage());
        }
    }
}

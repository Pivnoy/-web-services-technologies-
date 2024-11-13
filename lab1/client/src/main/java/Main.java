import org.example.standalone.service.ClusterVm;
import org.example.standalone.service.ClusterVmService;
import org.example.standalone.service.Filter;

//import org.example.service.ClusterVm;
//import org.example.service.ClusterVmService;
//import org.example.service.Filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static Filter createFilter(String fieldName, Object value) {
        Filter filter = new Filter();
        filter.setFieldName(fieldName);
        filter.setValue(value);
        return filter;
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9000/ClusterVmService?wsdl");
        //URL url = new URL("http://localhost:8080/javaee-1.0-SNAPSHOT/ClusterVmService?wsdl");
        ClusterVmService service = new ClusterVmService(url);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите опции для поиска: ");
            while (true) {
                List<Filter> filters = new ArrayList<>();

                System.out.print("id: ");
                String idInput = scanner.nextLine();
                if (idInput != null && !idInput.isEmpty()) {
                    UUID id = UUID.fromString(idInput);
                    filters.add(createFilter("id", id));
                }

                System.out.print("vmid: ");
                String vmidInput = scanner.nextLine();
                if (vmidInput != null && !vmidInput.isEmpty()) {
                    Integer vmid = Integer.parseInt(vmidInput);
                    filters.add(createFilter("vmid", vmid));
                }

                System.out.print("name: ");
                String name = scanner.nextLine();
                if (name != null && !name.isEmpty()) {
                    filters.add(createFilter("name", name));
                }

                System.out.print("image: ");
                String image = scanner.nextLine();
                if (image != null && !image.isEmpty()) {
                    filters.add(createFilter("image", image));
                }

                System.out.print("cpu: ");
                String cpuInput = scanner.nextLine();
                if (cpuInput != null && !cpuInput.isEmpty()) {
                    Integer cpu = Integer.parseInt(cpuInput);
                    filters.add(createFilter("cpu", cpu));
                }

                System.out.print("memory: ");
                String memoryInput = scanner.nextLine();
                if (memoryInput != null && !memoryInput.isEmpty()) {
                    Integer memory = Integer.parseInt(memoryInput);
                    filters.add(createFilter("memory", memory));
                }

                List<ClusterVm> vms = service.getClusterVmWebServicePort().getClusterVms(filters);
                if (!vms.isEmpty()) {
                    System.out.println("Получены данные: ");
                    for (ClusterVm vm : vms) {
                        System.out.println("ClusterVm{" +
                                "id=" + vm.getId() +
                                ", vmid=" + vm.getVmid() +
                                ", name='" + vm.getName() + '\'' +
                                ", image='" + vm.getImage() + '\'' +
                                ", cpu=" + vm.getCpu() +
                                ", memory=" + vm.getMemory() +
                                '}');
                    }
                } else {
                    System.out.println("Ничего не нашлось :(");
                }
            }
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }

    }
}
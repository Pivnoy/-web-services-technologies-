import java.util.List;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class Main {

//    private static final String URL =
//            "http://localhost:8080/javaee-1.0-SNAPSHOT/v1/vms";
private static final String URL =
        "http://localhost:8080/v1/vms";

    public static Filter createFilter(String fieldName, Object value) {
        Filter filter = new Filter();
        filter.setFieldName(fieldName);
        filter.setValue(value);
        return filter;
    }

    public static void main(String[] args) {
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);

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

                WebResource webResource = client.resource(URL);
                if (!filters.isEmpty()) {
                    for (Filter filter : filters) {
                        webResource = webResource.queryParam("filters", filter.formFilterStr());
                    }
                }

                ClientResponse clientResponse =  webResource.
                        accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
                if (clientResponse.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
                    throw new IllegalStateException("Request failed");
                }


                List<ClusterVm> vms = clientResponse.getEntity(new GenericType<List<ClusterVm>>() {});

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
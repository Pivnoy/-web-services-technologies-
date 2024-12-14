package command.impl;

import client.WebClient;
import command.ifc.Command;
import lombok.AllArgsConstructor;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;

import java.rmi.RemoteException;
import java.util.Scanner;

@AllArgsConstructor
public class FindCommand implements Command {

    private UDDIClerk clerk;
    private UDDIInquiryPortType inquiry;

    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Введите имя сервиса: ");
            String srvNameInput = scanner.nextLine();
            if (srvNameInput == null || srvNameInput.isEmpty()) {
                throw new IllegalArgumentException("некорректное имя сервиса!");
            }

            String accessPoint = findServiceByName(srvNameInput);
            if (accessPoint == null) {
                throw new IllegalArgumentException("не удалось найти сервис и подключиться к нему");
            }

            System.out.println("Сервис с именем " + srvNameInput + "найден! Адрес сервиса - " + accessPoint + "Устанавливаем подключение к сервису");

            WebClient.runClient(accessPoint, scanner);

        } catch (Exception e) {
            System.out.println("что-то пошло не так: " + e.getMessage());
        }
    }

    private String findServiceByName(String serviceName) throws RemoteException, TransportException {
            FindService findService = new FindService();
            findService.setAuthInfo(clerk.getAuthToken());

            FindQualifiers findQualifiers = new FindQualifiers();
            findQualifiers.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
            findService.setFindQualifiers(findQualifiers);
            Name searchName = new Name();
            searchName.setValue("%" + serviceName + "%");
            findService.getName().add(searchName);
            ServiceList serviceList = inquiry.findService(findService);
            if (serviceList == null || serviceList.getServiceInfos() == null) {
                return null;
            }

            GetServiceDetail gsd = new GetServiceDetail();
            for (int i = 0; i < serviceList.getServiceInfos().getServiceInfo().size(); i++) {
                gsd.getServiceKey().add(serviceList.getServiceInfos().getServiceInfo().get(i).getServiceKey());
            }

            ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
            if (serviceDetail != null) {
                return serviceDetail.getBusinessService().get(0).getBindingTemplates().getBindingTemplate().get(0).getAccessPoint().getValue();
            }

            return null;
    }
}

package command.impl;

import command.ifc.Command;
import lombok.AllArgsConstructor;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.uddi.api_v3.*;

import java.util.Scanner;

@AllArgsConstructor
public class RegisterCommand implements Command {

    private UDDIClerk clerk;

    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Введите имя сервиса: ");
            String srvNameInput = scanner.nextLine();
            if (srvNameInput == null || srvNameInput.isEmpty()) {
                throw new IllegalArgumentException("некорректное имя сервиса!");
            }

            System.out.print("Введите путь до сервиса: ");
            String srvPathInput = scanner.nextLine();
            if (srvPathInput == null || srvPathInput.isEmpty()) {
                throw new IllegalArgumentException("некорректный путь до сервиса!");
            }

            System.out.print("Введите описание сервиса: ");
            String srvDescInput = scanner.nextLine();
            if (srvDescInput == null || srvDescInput.isEmpty()) {
                throw new IllegalArgumentException("некорректное описание сервиса!");
            }

            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(srvNameInput);
            myBusEntity.getName().add(myBusName);

            Description myDescription = new Description();
            myDescription.setValue(srvDescInput);
            myBusEntity.getDescription().add(myDescription);

            BusinessEntity register = clerk.register(myBusEntity);
            if (register == null) {
                System.out.println("Save failed!");
                System.exit(1);
            }

            String myBusKey = register.getBusinessKey();
            System.out.println("Бизнес зарегистрирован, получен ключ: " + myBusKey);

            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(srvNameInput);
            myService.getName().add(myServName);

            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(srvPathInput);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(UDDIClient.addSOAPtModels(myBindingTemplate));
            myService.setBindingTemplates(myBindingTemplates);
            BusinessService svc = clerk.register(myService);
            if (svc==null){
                System.out.println("Save failed!");
                System.exit(1);
            }

            String myServKey = svc.getServiceKey();
            System.out.println("Сервис зарегистрирован, получен ключ:  " + myServKey);

            clerk.discardAuthToken();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

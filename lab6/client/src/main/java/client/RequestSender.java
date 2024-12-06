package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import dto.ClusterVm;
import dto.Filter;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

public class RequestSender {

    private final Client client;
    private final static String baseURL = "http://localhost:8080/v1/vms";

    public RequestSender() {
        ClientConfig clientConfig = new DefaultClientConfig();
        this.client = Client.create(clientConfig);
    }

    public String sendMutateRequest(String url, String auth, RequestType type, String json) {
        Builder webResource = client.resource(baseURL + url).header("Authorization", auth);
        ClientResponse clientResponse = null;

        switch (type) {
            case POST:
                clientResponse = webResource.
                        type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, json);
                break;
            case PUT:
                clientResponse =  webResource.
                        type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class, json);
                break;
            case DELETE:
                clientResponse =  webResource.
                        type(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
                break;
        }

        if (clientResponse != null) {
            if (clientResponse.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
                throw new IllegalStateException("Статус: " + clientResponse.getStatus() + "\n" +
                        "Сообщение: " + clientResponse.getEntity(String.class));
            }

            return clientResponse.getEntity(String.class);
        }

        return null;
    }

    public List<ClusterVm> sendRequest(String url, RequestType type, List<Filter> filters) {
        WebResource webResource = client.resource(baseURL + url);
        if (!filters.isEmpty()) {
            for (Filter filter : filters) {
                webResource = webResource.queryParam(filter.getFieldName(), filter.getValue().toString());
            }
        }

        ClientResponse clientResponse = null;

        if (Objects.requireNonNull(type) == RequestType.GET) {
            clientResponse = webResource.
                    type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        }

        if (clientResponse != null) {
            if (clientResponse.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
                throw new IllegalStateException("Статус: " + clientResponse.getStatus() + "\n" +
                        "Сообщение: " + clientResponse.getEntity(String.class));
            }

            return clientResponse.getEntity(new GenericType<List<ClusterVm>>() {});
        }

        return null;
    }
}

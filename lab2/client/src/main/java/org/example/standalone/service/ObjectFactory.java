
package org.example.standalone.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.standalone.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateClusterVm_QNAME = new QName("http://service.standalone.example.org/", "updateClusterVm");
    private final static QName _CreateClusterVm_QNAME = new QName("http://service.standalone.example.org/", "createClusterVm");
    private final static QName _UpdateClusterVmResponse_QNAME = new QName("http://service.standalone.example.org/", "updateClusterVmResponse");
    private final static QName _DeleteClusterVmResponse_QNAME = new QName("http://service.standalone.example.org/", "deleteClusterVmResponse");
    private final static QName _GetClusterVms_QNAME = new QName("http://service.standalone.example.org/", "getClusterVms");
    private final static QName _GetClusterVmsResponse_QNAME = new QName("http://service.standalone.example.org/", "getClusterVmsResponse");
    private final static QName _CreateClusterVmResponse_QNAME = new QName("http://service.standalone.example.org/", "createClusterVmResponse");
    private final static QName _DeleteClusterVm_QNAME = new QName("http://service.standalone.example.org/", "deleteClusterVm");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.standalone.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateClusterVm }
     * 
     */
    public UpdateClusterVm createUpdateClusterVm() {
        return new UpdateClusterVm();
    }

    /**
     * Create an instance of {@link CreateClusterVmResponse }
     * 
     */
    public CreateClusterVmResponse createCreateClusterVmResponse() {
        return new CreateClusterVmResponse();
    }

    /**
     * Create an instance of {@link DeleteClusterVm }
     * 
     */
    public DeleteClusterVm createDeleteClusterVm() {
        return new DeleteClusterVm();
    }

    /**
     * Create an instance of {@link GetClusterVmsResponse }
     * 
     */
    public GetClusterVmsResponse createGetClusterVmsResponse() {
        return new GetClusterVmsResponse();
    }

    /**
     * Create an instance of {@link GetClusterVms }
     * 
     */
    public GetClusterVms createGetClusterVms() {
        return new GetClusterVms();
    }

    /**
     * Create an instance of {@link DeleteClusterVmResponse }
     * 
     */
    public DeleteClusterVmResponse createDeleteClusterVmResponse() {
        return new DeleteClusterVmResponse();
    }

    /**
     * Create an instance of {@link CreateClusterVm }
     * 
     */
    public CreateClusterVm createCreateClusterVm() {
        return new CreateClusterVm();
    }

    /**
     * Create an instance of {@link UpdateClusterVmResponse }
     * 
     */
    public UpdateClusterVmResponse createUpdateClusterVmResponse() {
        return new UpdateClusterVmResponse();
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link ClusterVm }
     * 
     */
    public ClusterVm createClusterVm() {
        return new ClusterVm();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateClusterVm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "updateClusterVm")
    public JAXBElement<UpdateClusterVm> createUpdateClusterVm(UpdateClusterVm value) {
        return new JAXBElement<UpdateClusterVm>(_UpdateClusterVm_QNAME, UpdateClusterVm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateClusterVm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "createClusterVm")
    public JAXBElement<CreateClusterVm> createCreateClusterVm(CreateClusterVm value) {
        return new JAXBElement<CreateClusterVm>(_CreateClusterVm_QNAME, CreateClusterVm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateClusterVmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "updateClusterVmResponse")
    public JAXBElement<UpdateClusterVmResponse> createUpdateClusterVmResponse(UpdateClusterVmResponse value) {
        return new JAXBElement<UpdateClusterVmResponse>(_UpdateClusterVmResponse_QNAME, UpdateClusterVmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteClusterVmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "deleteClusterVmResponse")
    public JAXBElement<DeleteClusterVmResponse> createDeleteClusterVmResponse(DeleteClusterVmResponse value) {
        return new JAXBElement<DeleteClusterVmResponse>(_DeleteClusterVmResponse_QNAME, DeleteClusterVmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClusterVms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "getClusterVms")
    public JAXBElement<GetClusterVms> createGetClusterVms(GetClusterVms value) {
        return new JAXBElement<GetClusterVms>(_GetClusterVms_QNAME, GetClusterVms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClusterVmsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "getClusterVmsResponse")
    public JAXBElement<GetClusterVmsResponse> createGetClusterVmsResponse(GetClusterVmsResponse value) {
        return new JAXBElement<GetClusterVmsResponse>(_GetClusterVmsResponse_QNAME, GetClusterVmsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateClusterVmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "createClusterVmResponse")
    public JAXBElement<CreateClusterVmResponse> createCreateClusterVmResponse(CreateClusterVmResponse value) {
        return new JAXBElement<CreateClusterVmResponse>(_CreateClusterVmResponse_QNAME, CreateClusterVmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteClusterVm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.standalone.example.org/", name = "deleteClusterVm")
    public JAXBElement<DeleteClusterVm> createDeleteClusterVm(DeleteClusterVm value) {
        return new JAXBElement<DeleteClusterVm>(_DeleteClusterVm_QNAME, DeleteClusterVm.class, null, value);
    }

}

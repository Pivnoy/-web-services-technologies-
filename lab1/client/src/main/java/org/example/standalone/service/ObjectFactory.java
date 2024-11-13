
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

    private final static QName _GetClusterVms_QNAME = new QName("http://service.standalone.example.org/", "getClusterVms");
    private final static QName _GetClusterVmsResponse_QNAME = new QName("http://service.standalone.example.org/", "getClusterVmsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.standalone.service
     * 
     */
    public ObjectFactory() {
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

}

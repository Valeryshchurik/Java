
package soapservice.daoTour;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the daoTour package.
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

    private final static QName _Create_QNAME = new QName("http://daoTour/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://daoTour/", "createResponse");
    private final static QName _Delete_QNAME = new QName("http://daoTour/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://daoTour/", "deleteResponse");
    private final static QName _FindAllBurningTours_QNAME = new QName("http://daoTour/", "findAllBurningTours");
    private final static QName _FindAllBurningToursResponse_QNAME = new QName("http://daoTour/", "findAllBurningToursResponse");
    private final static QName _FindAllTours_QNAME = new QName("http://daoTour/", "findAllTours");
    private final static QName _FindAllToursResponse_QNAME = new QName("http://daoTour/", "findAllToursResponse");
    private final static QName _Read_QNAME = new QName("http://daoTour/", "read");
    private final static QName _ReadResponse_QNAME = new QName("http://daoTour/", "readResponse");
    private final static QName _Update_QNAME = new QName("http://daoTour/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://daoTour/", "updateResponse");
    private final static QName _TourEntityOrdersById_QNAME = new QName("", "ordersById");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: daoTour
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link FindAllBurningTours }
     * 
     */
    public FindAllBurningTours createFindAllBurningTours() {
        return new FindAllBurningTours();
    }

    /**
     * Create an instance of {@link FindAllBurningToursResponse }
     * 
     */
    public FindAllBurningToursResponse createFindAllBurningToursResponse() {
        return new FindAllBurningToursResponse();
    }

    /**
     * Create an instance of {@link FindAllTours }
     * 
     */
    public FindAllTours createFindAllTours() {
        return new FindAllTours();
    }

    /**
     * Create an instance of {@link FindAllToursResponse }
     * 
     */
    public FindAllToursResponse createFindAllToursResponse() {
        return new FindAllToursResponse();
    }

    /**
     * Create an instance of {@link Read }
     * 
     */
    public Read createRead() {
        return new Read();
    }

    /**
     * Create an instance of {@link ReadResponse }
     * 
     */
    public ReadResponse createReadResponse() {
        return new ReadResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link ClientEntity }
     * 
     */
    public ClientEntity createClientEntity() {
        return new ClientEntity();
    }

    /**
     * Create an instance of {@link OrderEntity }
     * 
     */
    public OrderEntity createOrderEntity() {
        return new OrderEntity();
    }

    /**
     * Create an instance of {@link TourEntity }
     * 
     */
    public TourEntity createTourEntity() {
        return new TourEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Create }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllBurningTours }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllBurningTours }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "findAllBurningTours")
    public JAXBElement<FindAllBurningTours> createFindAllBurningTours(FindAllBurningTours value) {
        return new JAXBElement<FindAllBurningTours>(_FindAllBurningTours_QNAME, FindAllBurningTours.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllBurningToursResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllBurningToursResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "findAllBurningToursResponse")
    public JAXBElement<FindAllBurningToursResponse> createFindAllBurningToursResponse(FindAllBurningToursResponse value) {
        return new JAXBElement<FindAllBurningToursResponse>(_FindAllBurningToursResponse_QNAME, FindAllBurningToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllTours }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllTours }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "findAllTours")
    public JAXBElement<FindAllTours> createFindAllTours(FindAllTours value) {
        return new JAXBElement<FindAllTours>(_FindAllTours_QNAME, FindAllTours.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllToursResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAllToursResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "findAllToursResponse")
    public JAXBElement<FindAllToursResponse> createFindAllToursResponse(FindAllToursResponse value) {
        return new JAXBElement<FindAllToursResponse>(_FindAllToursResponse_QNAME, FindAllToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Read }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Read }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "read")
    public JAXBElement<Read> createRead(Read value) {
        return new JAXBElement<Read>(_Read_QNAME, Read.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReadResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "readResponse")
    public JAXBElement<ReadResponse> createReadResponse(ReadResponse value) {
        return new JAXBElement<ReadResponse>(_ReadResponse_QNAME, ReadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://daoTour/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "ordersById", scope = TourEntity.class)
    @XmlIDREF
    public JAXBElement<Object> createTourEntityOrdersById(Object value) {
        return new JAXBElement<Object>(_TourEntityOrdersById_QNAME, Object.class, TourEntity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "ordersById", scope = ClientEntity.class)
    @XmlIDREF
    public JAXBElement<Object> createClientEntityOrdersById(Object value) {
        return new JAXBElement<Object>(_TourEntityOrdersById_QNAME, Object.class, ClientEntity.class, value);
    }

}

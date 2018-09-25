
package soapservice.daoTour;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for orderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clientByIdclient" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/&gt;
 *         &lt;element name="tourByIdtour" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="idclient" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="idtour" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderEntity", propOrder = {
    "clientByIdclient",
    "tourByIdtour"
})
public class OrderEntity {

    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object clientByIdclient;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object tourByIdtour;
    @XmlAttribute(name = "idclient")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idclient;
    @XmlAttribute(name = "idtour")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idtour;

    /**
     * Gets the value of the clientByIdclient property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getClientByIdclient() {
        return clientByIdclient;
    }

    /**
     * Sets the value of the clientByIdclient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setClientByIdclient(Object value) {
        this.clientByIdclient = value;
    }

    /**
     * Gets the value of the tourByIdtour property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTourByIdtour() {
        return tourByIdtour;
    }

    /**
     * Sets the value of the tourByIdtour property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTourByIdtour(Object value) {
        this.tourByIdtour = value;
    }

    /**
     * Gets the value of the idclient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdclient() {
        return idclient;
    }

    /**
     * Sets the value of the idclient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdclient(String value) {
        this.idclient = value;
    }

    /**
     * Gets the value of the idtour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdtour() {
        return idtour;
    }

    /**
     * Sets the value of the idtour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdtour(String value) {
        this.idtour = value;
    }

}

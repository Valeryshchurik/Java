//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.10 at 02:29:46 PM MSK 
//


package com.shchuryk.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Travel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Travel">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/vouchers}VoucherBuilderFactory">
 *       &lt;sequence>
 *         &lt;element name="hotel" type="{http://www.example.com/vouchers}Hotel"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Travel", propOrder = {
    "hotel"
})
public class Travel
    extends Voucher
{

    @XmlElement(required = true)
    protected Hotel hotel;

    /**
     * Gets the value of the hotel property.
     * 
     * @return
     *     possible object is
     *     {@link Hotel }
     *     
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets the value of the hotel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hotel }
     *     
     */
    public void setHotel(Hotel value) {
        this.hotel = value;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "hotel=" + hotel +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", numberDays=" + numberDays +
                ", cost=" + cost +
                ", transportType=" + transportType +
                '}';
    }
    public String toSecondaryString() {
        return "hotel=" + hotel;
    }
}

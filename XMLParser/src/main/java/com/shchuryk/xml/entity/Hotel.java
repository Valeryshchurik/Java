//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.10 at 02:29:46 PM MSK 
//


package com.shchuryk.xml.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for Hotel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Hotel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="meal" type="{http://www.example.com/vouchers}Meal"/>
 *         &lt;element name="type" type="{http://www.example.com/vouchers}Apartment-Type"/>
 *         &lt;element name="tv" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="air-conditioning" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="stars" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *             &lt;pattern value="[1-5]"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", propOrder = {
    "meal",
    "type",
    "tv",
    "airConditioning"
})
public class Hotel {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Meal meal;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ApartmentType type;
    protected boolean tv;
    @XmlElement(name = "air-conditioning")
    protected boolean airConditioning;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "stars", required = true)
    protected BigInteger stars;

    /**
     * Gets the value of the meal property.
     * 
     * @return
     *     possible object is
     *     {@link Meal }
     *     
     */
    public Meal getMeal() {
        return meal;
    }

    /**
     * Sets the value of the meal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Meal }
     *     
     */
    public void setMeal(Meal value) {
        this.meal = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ApartmentType }
     *     
     */
    public ApartmentType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApartmentType }
     *     
     */
    public void setType(ApartmentType value) {
        this.type = value;
    }

    /**
     * Gets the value of the tv property.
     * 
     */
    public boolean isTv() {
        return tv;
    }

    /**
     * Sets the value of the tv property.
     * 
     */
    public void setTv(boolean value) {
        this.tv = value;
    }

    /**
     * Gets the value of the airConditioning property.
     * 
     */
    public boolean isAirConditioning() {
        return airConditioning;
    }

    /**
     * Sets the value of the airConditioning property.
     * 
     */
    public void setAirConditioning(boolean value) {
        this.airConditioning = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the stars property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStars() {
        return stars;
    }

    /**
     * Sets the value of the stars property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStars(BigInteger value) {
        this.stars = value;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "meal=" + meal +
                ", type=" + type +
                ", tv=" + tv +
                ", airConditioning=" + airConditioning +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                '}';
    }
}
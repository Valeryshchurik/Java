//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.10 at 02:29:46 PM MSK 
//


package com.shchuryk.xml.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Meal.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Meal">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HB"/>
 *     &lt;enumeration value="BB"/>
 *     &lt;enumeration value="AL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Meal")
@XmlEnum
public enum Meal {

    HB,
    BB,
    AL;

    public String value() {
        return name();
    }

    public static Meal fromValue(String v) {
        return valueOf(v);
    }

}

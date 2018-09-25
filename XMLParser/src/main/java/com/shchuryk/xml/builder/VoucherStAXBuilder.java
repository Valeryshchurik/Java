package com.shchuryk.xml.builder;

import com.shchuryk.xml.entity.*;
import com.shchuryk.xml.exception.XmlParserException;
import com.shchuryk.xml.servlet.UploadServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

public class VoucherStAXBuilder extends AbstractVoucherBuilder {
    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private XMLInputFactory inputFactory;
    public VoucherStAXBuilder() {
        inputFactory=XMLInputFactory.newFactory();
    }
    @Override
    public void buildListVouchers(String fileName) throws XmlParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    switch (VoucherEnum.fromValue(name)) {
                        case EXCURSION:
                            Excursion excursion = new Excursion();
                            excursion=(Excursion)buildVoucher(reader, excursion);
                            vouchers.add(excursion);
                            break;
                        case TRAVEL:
                            Travel travel = new Travel();
                            travel = (Travel)buildVoucher(reader, travel);
                            vouchers.add(travel);
                            break;
                    }
                }
            }
        } catch (XMLStreamException ex) {
            throw new XmlParserException("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            throw new XmlParserException("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file "+fileName+" : "+e);
            }
        }
    }
    private Voucher buildVoucher(XMLStreamReader reader, Voucher voucher) throws XMLStreamException {
        String transportAttribute=reader.getAttributeValue(null,VoucherEnum.TRANSPORT_TYPE.toString());
        if (transportAttribute!=null)
            voucher.setTransportType(TransportType.fromValue(transportAttribute));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (VoucherEnum.fromValue(name)) {
                        case ID:
                            voucher.setId(getXMLText(reader));
                            break;
                        case COUNTRY:
                            voucher.setCountry(getXMLText(reader));
                            break;
                        case NUMBER_DAYS:
                            voucher.setNumberDays(BigInteger.valueOf(Integer.parseInt(getXMLText(reader))));
                            break;
                        case COST:
                            voucher.setCost(BigInteger.valueOf(Integer.parseInt(getXMLText(reader))));
                            break;
                        case PLACE:
                            ((Excursion)voucher).setPlace(getXMLText(reader));
                            break;
                        case HOTEL:
                            Hotel hotel = getXMLHotel(reader);
                            if (voucher instanceof Travel) {
                                ((Travel) voucher).setHotel(hotel);
                            }
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ((VoucherEnum.fromValue(name) == VoucherEnum.TRAVEL)||(VoucherEnum.fromValue(name) == VoucherEnum.EXCURSION)) {
                        return voucher;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }
    private Hotel getXMLHotel(XMLStreamReader reader) throws XMLStreamException {
        Hotel hotel = new Hotel();
        String hotelName = reader.getAttributeValue(null, VoucherEnum.NAME.toString().toLowerCase());
        String hotelRate = reader.getAttributeValue(null, VoucherEnum.STARS.toString().toLowerCase());
        hotel.setName(hotelName);
        hotel.setStars(BigInteger.valueOf(Integer.valueOf(hotelRate)));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (VoucherEnum.fromValue(name)) {
                        case MEAL:
                            hotel.setMeal(Meal.fromValue(getXMLText(reader)));
                            break;
                        case APARTMENT_TYPE:
                            hotel.setType(ApartmentType.fromValue(getXMLText(reader)));
                            break;
                        case TV:
                            hotel.setTv(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case AIR_CONDITIONING:
                            hotel.setAirConditioning(Boolean.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (VoucherEnum.fromValue(name) == VoucherEnum.HOTEL){
                        return hotel;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Address");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

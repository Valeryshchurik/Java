package com.shchuryk.xml.builder;


import com.shchuryk.xml.entity.*;
import com.shchuryk.xml.exception.XmlParserException;
import com.shchuryk.xml.servlet.UploadServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class VoucherDOMBuilder extends AbstractVoucherBuilder {
    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private DocumentBuilder docBuilder;
    public VoucherDOMBuilder() throws XmlParserException {
        this.vouchers = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e){
            throw new XmlParserException("Error while configuring parser occurred:" + e);
        }
    }
    @Override
    public void buildListVouchers(String fileName) throws XmlParserException {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList excursionsList = root.getElementsByTagName("excursion");
            for (int i = 0; i < excursionsList.getLength(); i++) {
                Element excursionElement = (Element) excursionsList.item(i);
                Voucher voucher = buildExcursion(excursionElement);
                vouchers.add(voucher);
            }
            doc = docBuilder.parse(fileName);
            root = doc.getDocumentElement();
            NodeList travelsList = root.getElementsByTagName("travel");
            for (int j = 0; j < travelsList.getLength(); j++) {
                Element travelElement = (Element) travelsList.item(j);
                Voucher voucher = buildTravel(travelElement);
                vouchers.add(voucher);
            }
        } catch (IOException e) {
            throw new XmlParserException("File error or I/O error: " + e);
        } catch (SAXException e) {
            throw new XmlParserException("Parsing failure: " + e);
        }
    }

    private Voucher buildVoucher(Element voucherElement, Voucher voucher) {
            String transportAttribute = voucherElement.getAttribute("transport-type");
            if (transportAttribute != null)
                voucher.setTransportType(TransportType.fromValue(transportAttribute));
            voucher.setId(getElementTextContent(voucherElement, "id"));
            voucher.setCountry(getElementTextContent(voucherElement, "country"));
            Integer numberDays = Integer.parseInt(getElementTextContent(voucherElement, "days-number"));
            voucher.setNumberDays(BigInteger.valueOf(numberDays));
            Integer cost = Integer.parseInt(getElementTextContent(voucherElement, "cost"));
            voucher.setCost(BigInteger.valueOf(cost));
            return voucher;
    }

    private Travel buildTravel(Element travelElement) {
        Travel travel = new Travel();
        travel=(Travel)buildVoucher(travelElement, travel);
        Hotel hotel=new Hotel();
        Element hotelElement = (Element) travelElement.getElementsByTagName("hotel").item(0);
        Integer stars = Integer.parseInt(hotelElement.getAttribute("stars"));
        hotel.setStars(BigInteger.valueOf(stars));
        hotel.setName(hotelElement.getAttribute("name"));
        hotel.setMeal(Meal.fromValue(getElementTextContent(hotelElement, "meal")));
        hotel.setType(ApartmentType.fromValue(getElementTextContent(hotelElement, "type")));
        hotel.setTv(Boolean.parseBoolean(getElementTextContent(hotelElement, "tv")));
        hotel.setAirConditioning(Boolean.parseBoolean(getElementTextContent(hotelElement, "air-conditioning")));
        travel.setHotel(hotel);
        return travel;
    }

    private Excursion buildExcursion(Element excursionElement) {
        Excursion excursion = new Excursion();
        excursion=(Excursion)buildVoucher(excursionElement, excursion);
        excursion.setPlace(getElementTextContent(excursionElement,"place"));
        return excursion;
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
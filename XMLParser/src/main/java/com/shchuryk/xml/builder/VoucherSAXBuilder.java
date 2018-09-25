package com.shchuryk.xml.builder;

import com.shchuryk.xml.exception.XmlParserException;
import com.shchuryk.xml.servlet.UploadServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


public class VoucherSAXBuilder extends AbstractVoucherBuilder {
    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private VoucherSAXHandler saxHandler;
    private XMLReader reader;
    public VoucherSAXBuilder() throws XmlParserException {
        saxHandler=new VoucherSAXHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(saxHandler);
        }catch (SAXException e){
            throw new XmlParserException("ошибка SAX парсера: " + e);
        }
    }
    @Override
    public void buildListVouchers(String fileName) throws XmlParserException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new XmlParserException("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            throw new XmlParserException("ошибка I/О потока: " + e);
        }
        vouchers = saxHandler.getVouchers();
    }
}
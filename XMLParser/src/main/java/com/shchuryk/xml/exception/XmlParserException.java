package com.shchuryk.xml.exception;

public class XmlParserException extends Exception {
    public XmlParserException() {
        super();
    }

    public XmlParserException(String s) {
        super(s);
    }

    public XmlParserException(String s, Throwable t) {
        super (s, t);
    }
}

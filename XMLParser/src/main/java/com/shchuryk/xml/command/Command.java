package com.shchuryk.xml.command;

import com.shchuryk.xml.exception.XmlParserException;
import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {
    void execute() throws IOException, ServletException, XmlParserException;
}

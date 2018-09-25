package com.shchuryk.command;

import com.shchuryk.exception.XmlParserException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ActionCommand {
    String execute(HttpServletRequest request);
}

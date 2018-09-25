package com.shchuryk.xml.servlet;

import com.shchuryk.xml.command.ParseCommand;
import com.shchuryk.xml.exception.XmlParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/UploadFiles")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class UploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle rb = ResourceBundle.getBundle("text", locale);
        LOGGER.info("called doPost() from UploadServlet");
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        LOGGER.info("Upload File Directory = " + fileSaveDir.getAbsolutePath());
        LOGGER.info("is dir: " + fileSaveDir.isDirectory() + ", is file: " + fileSaveDir.isFile());

        String fileName = null;

        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                response.getWriter().print(part.getSubmittedFileName() + "was uploaded successfully");
            }
            fileName = part.getSubmittedFileName();
        }
        LOGGER.info("filename: " + fileName);
        File file = new File(uploadFilePath + File.separator + fileName);
        LOGGER.info("is dir: " + file.isDirectory() + ", is file: " + file.isFile());
        parseFile(uploadFilePath + File.separator + fileName, request);
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }

    private void parseFile(String fileName,
                           HttpServletRequest request) {
        if (fileName != null) {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaLocation;
            Source source;
            try {
                schemaLocation = new File("resources/vouchers.xsd");
                Schema schema = factory.newSchema(schemaLocation);
                Validator validator = schema.newValidator();
                source = new StreamSource(fileName);
                validator.validate(source);
                new ParseCommand(request, fileName).execute();
            } catch (SAXException e) {
                LOGGER.log(Level.ERROR, fileName + " file is invalid:" + e);
            } catch (IOException | XmlParserException e) {
                LOGGER.catching(e);
            }
        }
    }
}

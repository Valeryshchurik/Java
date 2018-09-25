package com.shchuryk.xml.command;

import com.shchuryk.xml.builder.AbstractVoucherBuilder;
import com.shchuryk.xml.builder.VoucherBuilderFactory;
import com.shchuryk.xml.entity.Voucher;
import com.shchuryk.xml.exception.XmlParserException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ParseCommand implements Command {
    private static Logger LOGGER= LogManager.getLogger(ParseCommand.class);
    private HttpServletRequest request;
    private String filePath;
    private VoucherBuilderFactory factory=new VoucherBuilderFactory();

    private final String STAX_TYPE = "STAX";
    private final String SAX_TYPE = "SAX";
    private final String DOM_TYPE = "DOM";

    public ParseCommand(HttpServletRequest request, String filePath) {
        this.request = request;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws XmlParserException {
        String parserType = request.getParameter("parserType");
        AbstractVoucherBuilder voucherBuilder;
        List<Voucher> vouchers;
        if (parserType != null) {
                switch (parserType) {
                    case DOM_TYPE:
                        voucherBuilder = factory.createStudentBuilder("dom");
                        break;
                    case SAX_TYPE:
                        voucherBuilder = factory.createStudentBuilder("sax");
                        break;
                    case STAX_TYPE:
                        voucherBuilder = factory.createStudentBuilder("stax");
                        break;
                    default:
                        throw new XmlParserException("Unknown parser");
                }
                try {
                    voucherBuilder.buildListVouchers(filePath);
                    vouchers = voucherBuilder.getVouchers();
                    request.setAttribute("list", vouchers);
                }
                catch (XmlParserException e) {
                    LOGGER.error(e);
                }
            }
        }
    }

package com.shchuryk.xml;

import com.shchuryk.xml.builder.AbstractVoucherBuilder;
import com.shchuryk.xml.builder.VoucherBuilderFactory;
import com.shchuryk.xml.entity.Voucher;
import com.shchuryk.xml.exception.XmlParserException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SaxParserTest {
    VoucherBuilderFactory factory=new VoucherBuilderFactory();
    @Test
    public void saxParserTest() throws XmlParserException {
        AbstractVoucherBuilder voucherSAXBuilder = factory.createStudentBuilder("sax");
        voucherSAXBuilder.buildListVouchers("src/main/resources/vouchers.xml");
        List<Voucher> vouchers = voucherSAXBuilder.getVouchers();
        for (Voucher voucher:vouchers) {
            System.out.println(voucher.toString());
        }
        Assert.assertEquals(vouchers.size(), 16);
    }

    @Test (expectedExceptions = XmlParserException.class)
    public void saxParserNegativeTest() throws XmlParserException {
        AbstractVoucherBuilder voucherSAXBuilder = factory.createStudentBuilder("sax");
        voucherSAXBuilder.buildListVouchers("src/main/resources/vouchrs.xml");
        List<Voucher> vouchers = voucherSAXBuilder.getVouchers();
    }
}

package com.shchuryk.xml;

import com.shchuryk.xml.builder.AbstractVoucherBuilder;
import com.shchuryk.xml.builder.VoucherBuilderFactory;
import com.shchuryk.xml.entity.Voucher;
import com.shchuryk.xml.exception.XmlParserException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

public class DomParserTest {

    VoucherBuilderFactory factory = new VoucherBuilderFactory();

    @Test
    public void domParserTest() throws XmlParserException {
        AbstractVoucherBuilder voucherDOMBuilder = factory.createStudentBuilder("dom");
        voucherDOMBuilder.buildListVouchers("src/main/resources/vouchers.xml");
        List<Voucher> vouchers = voucherDOMBuilder.getVouchers();
        for (Voucher voucher : vouchers) {
            System.out.println(voucher.toString());
        }
        Assert.assertEquals(vouchers.size(), 16);
    }

    @Test (expectedExceptions = XmlParserException.class)
    public void domParserNegativeTest() throws XmlParserException {
        AbstractVoucherBuilder voucherDOMBuilder = factory.createStudentBuilder("dom");
        voucherDOMBuilder.buildListVouchers("src/main/resources/vouchrs.xml");
        List<Voucher> vouchers = voucherDOMBuilder.getVouchers();
        for (Voucher voucher:vouchers) {
            System.out.println(voucher.toString());
        }
        Assert.assertEquals(vouchers.size(), 16);
    }
}

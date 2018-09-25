package com.shchuryk.xml.builder;

import com.shchuryk.xml.entity.Voucher;
import com.shchuryk.xml.exception.XmlParserException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVoucherBuilder {

    protected List<Voucher> vouchers;

    public AbstractVoucherBuilder() {
        vouchers = new ArrayList<>();
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    abstract public void buildListVouchers(String fileName) throws XmlParserException;
}

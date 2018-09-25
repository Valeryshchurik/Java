package com.shchuryk.xml.builder;

import com.shchuryk.xml.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigInteger;
import java.util.*;

public class VoucherSAXHandler extends DefaultHandler {
    private List<Voucher> vouchers;
    private Voucher currentVoucher = null;
    private Hotel currentHotel = null;
    private VoucherEnum currentEnum = null;
    private EnumSet<VoucherEnum> withText;

    public VoucherSAXHandler() {
        vouchers = new ArrayList<>();
        withText = EnumSet.range(VoucherEnum.EXCURSION, VoucherEnum.AIR_CONDITIONING);
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        VoucherEnum currentTag = VoucherEnum.fromValue(localName);
        switch (currentTag) {
            case EXCURSION:
                currentVoucher = new Excursion();
                if (attrs.getLength() == 1) {
                    currentVoucher.setTransportType(TransportType.fromValue(attrs.getValue(0)));
                }
                break;
            case TRAVEL:
                currentVoucher = new Travel();
                if (attrs.getLength() == 1) {
                    currentVoucher.setTransportType(TransportType.valueOf(attrs.getValue(0).toUpperCase()));
                }
                break;
            case HOTEL:
                currentHotel = new Hotel();
                currentHotel.setStars(BigInteger.valueOf(Integer.parseInt(attrs.getValue(0))));
                currentHotel.setName(attrs.getValue(1));
                break;
            default:
                VoucherEnum temp = VoucherEnum.fromValue(localName);
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (("travel".equals(localName)) || ("excursion".equals(localName))) {
            vouchers.add(currentVoucher);
        }
        if ("hotel".equals(localName)) {
            ((Travel) currentVoucher).setHotel(currentHotel);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case TOURIST_VOUCHERS:
                    break;
                case ID:
                    currentVoucher.setId(s);
                    break;
                case COUNTRY:
                    currentVoucher.setCountry(s);
                    break;
                case NUMBER_DAYS:
                    currentVoucher.setNumberDays(BigInteger.valueOf(Integer.parseInt(s)));
                    break;
                case COST:
                    currentVoucher.setCost(BigInteger.valueOf(Integer.parseInt(s)));
                    break;
                case PLACE:
                    ((Excursion) currentVoucher).setPlace(s);
                    break;
                case MEAL:
                    currentHotel.setMeal(Meal.fromValue(s));
                    break;
                case APARTMENT_TYPE:
                    currentHotel.setType(ApartmentType.fromValue(s));
                    break;
                case TV:
                    currentHotel.setTv(Boolean.valueOf(s));
                    break;
                case AIR_CONDITIONING:
                    currentHotel.setAirConditioning(Boolean.valueOf(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}

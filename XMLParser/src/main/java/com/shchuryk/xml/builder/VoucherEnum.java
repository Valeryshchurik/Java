package com.shchuryk.xml.builder;

public enum VoucherEnum {
    TOURIST_VOUCHERS("tourist-vouchers"),
    EXCURSION("excursion"),
    TRAVEL("travel"),
    ID("id"),
    COUNTRY("country"),
    NUMBER_DAYS("days-number"),
    COST("cost"),
    TRANSPORT_TYPE("transport-type"),
    PLACE("place"),
    HOTEL("hotel"),
    APARTMENT_TYPE("type"),
    MEAL("meal"),
    TV("tv"),
    AIR_CONDITIONING("air-conditioning"),
    NAME("name"),
    STARS("stars");
    private String value;
    VoucherEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static VoucherEnum fromValue(String v) {
        for (VoucherEnum c: VoucherEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

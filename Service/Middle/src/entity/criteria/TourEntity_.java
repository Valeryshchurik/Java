package entity.criteria;


import entity.OrderEntity;
import entity.TourEntity;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.sql.Date;

@StaticMetamodel(TourEntity.class)
public class TourEntity_ {
    public static volatile SingularAttribute<TourEntity, Integer> id;
    public static volatile SingularAttribute<TourEntity, String> name;
    public static volatile SingularAttribute<TourEntity, String> country;
    public static volatile SingularAttribute<TourEntity, Date> startDate;
    public static volatile SingularAttribute<TourEntity, Date> endDate;
    public static volatile SingularAttribute<TourEntity, String> type;
    public static volatile SingularAttribute<TourEntity, Integer> price;
    public static volatile CollectionAttribute<TourEntity, OrderEntity> ordersById;
    public static volatile SingularAttribute<TourEntity, Byte> burning;
    public static volatile SingularAttribute<TourEntity, BigDecimal> discounts;
}

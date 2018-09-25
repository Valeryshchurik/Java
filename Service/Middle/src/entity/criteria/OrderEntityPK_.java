package entity.criteria;


import entity.OrderEntityPK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderEntityPK.class)
public class OrderEntityPK_ {
    public static volatile SingularAttribute<OrderEntityPK, Integer> idclient;
    public static volatile SingularAttribute<OrderEntityPK, Integer> idtour;
}

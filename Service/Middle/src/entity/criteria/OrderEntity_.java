package entity.criteria;


import entity.ClientEntity;
import entity.OrderEntity;
import entity.TourEntity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderEntity.class)
public class OrderEntity_ {
    public static volatile SingularAttribute<OrderEntity, ClientEntity> client;
    public static volatile SingularAttribute<OrderEntity, TourEntity> getTourByOrderId;
    public static volatile SingularAttribute<OrderEntity, Integer> idclient;
    public static volatile SingularAttribute<OrderEntity, Integer> idtour;
}

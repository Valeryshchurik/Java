package entity.criteria;


import entity.ClientEntity;
import entity.OrderEntity;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ClientEntity.class)
public class ClientEntity_ {
    public static volatile SingularAttribute<ClientEntity, String> name;
    public static volatile SingularAttribute<ClientEntity, Integer> id;
    public static volatile CollectionAttribute<ClientEntity, OrderEntity> ordersById;
}

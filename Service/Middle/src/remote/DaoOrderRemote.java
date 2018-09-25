package remote;

import entity.OrderEntity;

import javax.ejb.Remote;

@Remote
public interface DaoOrderRemote {

    OrderEntity create(OrderEntity entity);


    OrderEntity update(OrderEntity newEntity);


    boolean delete(int clientId, int tourId);


    OrderEntity read(int clientId, int tourId);
}

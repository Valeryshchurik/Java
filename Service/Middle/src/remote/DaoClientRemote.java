package remote;

import entity.ClientEntity;
import entity.TourEntity;

import javax.ejb.Remote;
import java.util.List;

/**
 * Remote interface for Client DAO bean.
 */
@Remote
public interface DaoClientRemote {
    /**
     * Adds client to database.
     * @param entity Entity to insert.
     * @return <b>true</b> if successful.
     */
    ClientEntity create(ClientEntity entity);

    /**
     * Updates client info in the database.
     * @param newEntity Entity to update.
     * @return <b>true</b> if successful.
     */
    ClientEntity update(ClientEntity newEntity);

    /**
     * Deletes client from database.
     * @param id Client id to delete.
     * @return <b>true</b> if successful.
     */
    boolean delete(int id);

    /**
     * Reads client by id.
     * @param id Client id.
     * @return Client with specified id.
     */
    ClientEntity read(int id);


    List<TourEntity> findClientOrders(String name);
}

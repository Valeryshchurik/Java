package dao;


import entity.ClientEntity;
import entity.OrderEntity;
import entity.TourEntity;
import entity.criteria.ClientEntity_;
import entity.criteria.OrderEntity_;
import entity.criteria.TourEntity_;
import remote.DaoClientRemote;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "DaoClientEJB")
@WebService
public class DaoClientBean implements DaoClientRemote {

    @PersistenceContext(unitName = "TravelPersistenceUnit")
    private EntityManager connection;

    @SuppressWarnings("Duplicates")
    public DaoClientBean() {
        super();
    }

    /**
     * Adds client to database.
     *
     * @param entity Entity to insert.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public ClientEntity create(ClientEntity entity) {
        connection.persist(entity);
        connection.flush();
        return entity;
    }

    /**
     * Updates client info in the database.
     *
     * @param newEntity Entity to update.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public ClientEntity update(ClientEntity newEntity) {
        ClientEntity entity = connection.find(ClientEntity.class, newEntity.getId());
        if (entity != null)
            entity.setName(newEntity.getName());
        return entity;
    }

    /**
     * Deletes client from database.
     *
     * @param id - id to delete.
     * @return <b>true</b> if successful.
     */
    @SuppressWarnings("Duplicates")
    @WebMethod
    public boolean delete(int id) {
        ClientEntity entity = connection.find(ClientEntity.class, id);
        if (entity == null)
            return false;
        connection.remove(entity);
        return true;
    }

    /**
     * Reads client by id.
     *
     * @param id Client id.
     * @return Client with specified id.
     */
    @WebMethod
    public ClientEntity read(int id) {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> findByIdQuery = cb.createQuery(ClientEntity.class);
        Root<ClientEntity> root = findByIdQuery.from(ClientEntity.class);
        ParameterExpression<Integer> idExpr = cb.parameter(Integer.class);
        findByIdQuery.select(root).where(cb.equal(root.get(ClientEntity_.id), idExpr));
        TypedQuery<ClientEntity> query = connection.createQuery(findByIdQuery);
        query.setParameter(idExpr, id);
        ClientEntity client = query.getSingleResult();
        return client;
    }
    @SuppressWarnings("Duplicates")
    private TourEntity readTour(int id) {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<TourEntity> findByIdQuery = cb.createQuery(TourEntity.class);
        Root<TourEntity> root = findByIdQuery.from(TourEntity.class);
        ParameterExpression<Integer> idExpr = cb.parameter(Integer.class);
        findByIdQuery.select(root).where(cb.equal(root.get(TourEntity_.id), idExpr));
        TypedQuery<TourEntity> query = connection.createQuery(findByIdQuery);
        query.setParameter(idExpr, id);
        TourEntity Tour = query.getSingleResult();
        return Tour;
    }
    @WebMethod
    public List<TourEntity> findClientOrders(String name) {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<Object[]> findOrdersQuery = cb.createQuery(Object[].class);
        Root<ClientEntity> c = findOrdersQuery.from(ClientEntity.class);
        Join<ClientEntity, OrderEntity> b = c.join(ClientEntity_.ordersById, JoinType.INNER);
        ParameterExpression<String> clientName = cb.parameter(String.class);
        findOrdersQuery.select(cb.array(b.get(OrderEntity_.idtour)))
                .where(cb.equal(c.get(ClientEntity_.name), clientName));
        Query query = connection.createQuery(findOrdersQuery);
        query.setParameter(clientName, name);
        List resultSet = query.getResultList();
        List<TourEntity> tours = new ArrayList<>();
        for (Object tourId: resultSet) {
            tours.add(readTour((Integer)tourId));
        }
        return tours;
    }
}

package dao;

import entity.ClientEntity;
import entity.OrderEntity;
import entity.OrderEntityPK;
import entity.TourEntity;
import entity.criteria.OrderEntity_;
import remote.DaoOrderRemote;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Stateless(mappedName = "DaoOrderEJB")
@WebService
public class DaoOrderBean implements DaoOrderRemote {
    @PersistenceContext(unitName = "TravelPersistenceUnit")
    private EntityManager connection;

    /**
     * Adds bet to database.
     *
     * @param entity Entity to insert.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public OrderEntity create(OrderEntity entity) {
        entity.setClientByIdclient(connection.find(ClientEntity.class, entity.getIdclient()));
        entity.setTourByIdtour(connection.find(TourEntity.class, entity.getIdtour()));
        connection.persist(entity);
        connection.flush();
        return entity;
    }

    /**
     * Updates bet info in the database.
     *
     * @param newEntity Entity to update.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public OrderEntity update(OrderEntity newEntity) {
        OrderEntityPK pk = new OrderEntityPK();
        pk.setIdclient(newEntity.getIdclient());
        pk.setIdtour(newEntity.getIdtour());
        OrderEntity entity = connection.find(OrderEntity.class, pk);
        return entity;
    }

    /**
     * Deletes bet from database.
     *
     * @return <b>true</b> if successful.
     */
    @SuppressWarnings("Duplicates")
    @WebMethod
    public boolean delete(int clientId, int tourId) {
        OrderEntityPK pk = new OrderEntityPK();
        pk.setIdclient(clientId);
        pk.setIdtour(tourId);
        OrderEntity entity = connection.find(OrderEntity.class, pk);
        if (entity == null)
            return false;
        connection.remove(entity);
        return true;
    }

    /**
     * Reads
     *
     * @return .
     */
    @WebMethod
    public OrderEntity read(int clientId, int tourId) {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> findByIdQuery = cb.createQuery(OrderEntity.class);
        Root<OrderEntity> root = findByIdQuery.from(OrderEntity.class);
        ParameterExpression<Integer> clientIdExpr = cb.parameter(Integer.class);
        ParameterExpression<Integer> tourIdExpr = cb.parameter(Integer.class);
        findByIdQuery.select(root).where(cb.and(cb.equal(root.get(OrderEntity_.idclient), clientIdExpr),
                cb.equal(root.get(OrderEntity_.idtour), tourIdExpr)));
        TypedQuery<OrderEntity> query = connection.createQuery(findByIdQuery);
        query.setParameter(clientIdExpr, clientId);
        query.setParameter(tourIdExpr, tourId);
        OrderEntity order = query.getSingleResult();
        return order;
    }

//    public List findOrdersByIdClient(int clientId) {
//        EntityManager connection = connector.getEntityManager();
//        TypedQuery<OrderEntity> query = connection.createNamedQuery("OrderEntity.findById", OrderEntity.class);
//        query.setParameter("id", clientId);
//        List resultSet = query.getResultList();
//        connection.close();
//        return resultSet;
//    }
}

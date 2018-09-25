package dao;


import entity.TourEntity;
import entity.criteria.TourEntity_;
import remote.DaoTourRemote;

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
import java.util.List;

@Stateless(mappedName = "DaoTourEJB")
@WebService
public class DaoTourBean implements DaoTourRemote {
    @PersistenceContext(unitName = "TravelPersistenceUnit")
    private EntityManager connection;

    @SuppressWarnings("Duplicates")
    public DaoTourBean() {
        super();
    }

    /**
     * Adds Tour to database.
     *
     * @param entity Entity to insert.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public TourEntity create(TourEntity entity) {
        connection.persist(entity);
        connection.flush();
        return entity;
    }

    /**
     * Updates Tour info in the database.
     *
     * @param newEntity Entity to update.
     * @return <b>true</b> if successful.
     */
    @WebMethod
    public TourEntity update(TourEntity newEntity) {
        TourEntity entity = connection.find(TourEntity.class, newEntity.getId());
        if (entity != null)
            entity.setName(newEntity.getName());
        return entity;
    }

    /**
     * Deletes Tour from database.
     *
     * @param id - id to delete.
     * @return <b>true</b> if successful.
     */
    @SuppressWarnings("Duplicates")
    @WebMethod
    public boolean delete(int id) {
        TourEntity entity = connection.find(TourEntity.class, id);
        if (entity == null)
            return false;
        connection.remove(entity);
        return true;
    }

    /**
     * Reads Tour by id.
     *
     * @param id Tour id.
     * @return Tour with specified id.
     */
    @WebMethod
    public TourEntity read(int id) {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<TourEntity> findByIdQuery = cb.createQuery(TourEntity.class);
        Root<TourEntity> root = findByIdQuery.from(TourEntity.class);
        ParameterExpression<Integer> idExpr = cb.parameter(Integer.class);
        findByIdQuery.select(root).where(cb.equal(root.get(String.valueOf(TourEntity_.id)), idExpr));
        TypedQuery<TourEntity> query = connection.createQuery(findByIdQuery);
        query.setParameter(idExpr, id);
        TourEntity Tour = query.getSingleResult();
        return Tour;
    }
    @WebMethod
    public List<TourEntity> findAllTours() {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<TourEntity> findToursQuery = cb.createQuery(TourEntity.class);
        Root<TourEntity> root = findToursQuery.from(TourEntity.class);
        CriteriaQuery<TourEntity> all = findToursQuery.select(root);
        TypedQuery<TourEntity> allQuery = connection.createQuery(all);
        return allQuery.getResultList();
    }
    @WebMethod
    public List<TourEntity> findAllBurningTours() {
        CriteriaBuilder cb = connection.getCriteriaBuilder();
        CriteriaQuery<TourEntity> findToursQuery = cb.createQuery(TourEntity.class);
        Root<TourEntity> root = findToursQuery.from(TourEntity.class);
        CriteriaQuery<TourEntity> all = findToursQuery.select(root).where(cb.equal(root.get(TourEntity_.burning), 1));
        TypedQuery<TourEntity> allQuery = connection.createQuery(all);
        return allQuery.getResultList();
    }
}

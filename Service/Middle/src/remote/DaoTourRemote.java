package remote;

import entity.TourEntity;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface DaoTourRemote {
    TourEntity create(TourEntity entity);


    TourEntity update(TourEntity newEntity);


    boolean delete(int id);


    TourEntity read(int id);

    List<TourEntity> findAllTours();

    List<TourEntity> findAllBurningTours();

}

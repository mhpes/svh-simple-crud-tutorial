import entities.Address;
import services.impl.ServicePetshopImpl;
import services.IServicePetshop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public class main {

    public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        try {
            factory = Persistence.createEntityManagerFactory("PersistenceUnit");
            entityManager = factory.createEntityManager();

            IServicePetshop petshopService = new ServicePetshopImpl();

            List<Address> petshopAddressList = petshopService.findAllAddress();

            //persistPerson(entityManager);
        } catch (Exception e) {
            //LOGGER.log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }

}

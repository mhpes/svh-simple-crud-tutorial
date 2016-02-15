package daos;

import entities.Address;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public interface AbstractDao {
    public List<Address> findAll();
    public Address findById(int id);
    public void create(Address address);
    public void update(Address address);
    public void delete(Address address);
    public void save(Address address);
    public List<Address> findAll(Address address);
    public List<Address> findAny(Address address);
}

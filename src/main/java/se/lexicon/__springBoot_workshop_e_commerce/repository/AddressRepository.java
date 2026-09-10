package se.lexicon.__springBoot_workshop_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
//=== Required Queries ===
    //    Find all addresses in a specific zip code area.
    List<Address> findAllByZipCode(String zipCode);


//=== Optional / Advanced Queries ===
    //    Find all addresses in a specific city.
    List<Address> findAllByCity(String city);

    //    Find addresses by street name.
    List<Address> findByStreet(String street);

    //    Count how many customers live in a given zip code.
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.address.zipCode = :zipCode")
    long countCustomersByZipCode(@Param("zipCode") String zipCode);

    //    Find addresses where zip code starts with a prefix.
    List<Address> findByZipCodeStartsWith(String prefix);
}

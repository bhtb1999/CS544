package repositories;

import domain.Address;
import domain.CD;
import domain.Customer;
import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Long> , JpaSpecificationExecutor<CD> {
//    List<CD> findByArtistAndPriceGreaterThan(String artist, double price);
//    List<CD> findByArtist(String artist);
    // Method names
    List<CD> findByArtistAndPriceLessThan(String artist, double price);
    // Named queries
    List<CD> findByArtist(String artist);

    // JPQL queries with @Query
    @Query("SELECT cd FROM CD cd WHERE cd.artist = :artist AND cd.price > :price")
    List<CD> findCDByArtistAndPriceBiggerThan(@Param("artist") String artist, @Param("price") double price);

    // Native query
    @Query(value = "SELECT * FROM cd JOIN product p on cd.id = p.id WHERE artist = 'U2'", nativeQuery = true)
    List<CD> getCDsFromU2();

//    @Query(value = "select * from cd inner join product on cd.id=product.id where artist = :artist",nativeQuery = true)
//    List<CD> findByAnArtist(@Param("artist")String artist);
//
//    @Query("select cd from CD cd where cd.artist= :artist and cd.price > :price")
//    List<CD> getAllCDsFromArtistAndPriceBiggerThan(@Param("artist")String artist, @Param("price")double price);
}

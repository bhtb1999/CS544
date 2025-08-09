package prompt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import prompt.domains.House;
import prompt.services.HouseService;

@SpringBootTest
public class HouseSearchTest {

    @Autowired
    private HouseService houseService;

    @Test
    public void testHouseSearch() {
        // Test search for pet-friendly houses
        List<House> petFriendlyHouses = houseService.searchSimilarHouses("pet friendly", 5);
        assertNotNull(petFriendlyHouses);
        assertTrue(petFriendlyHouses.size() > 0);

        // Verify that returned houses are actually pet-friendly
        for (House house : petFriendlyHouses) {
            assertTrue(house.getIsPetFriendly());
        }

        System.out.println("Found " + petFriendlyHouses.size() + " pet-friendly houses:");
        for (House house : petFriendlyHouses) {
            System.out
                    .println("- " + house.getAddress() + ", " + house.getCity() + " ($" + house.getRent() + "/month)");
        }
    }

    @Test
    public void testPoolSearch() {
        // Test search for houses with pools
        List<House> poolHouses = houseService.searchSimilarHouses("pool", 3);
        assertNotNull(poolHouses);

        System.out.println("Found " + poolHouses.size() + " houses with pools:");
        for (House house : poolHouses) {
            System.out.println(
                    "- " + house.getAddress() + ", " + house.getCity() + " (Pool: " + house.getHasPool() + ")");
        }
    }

    @Test
    public void testDowntownSearch() {
        // Test search for downtown properties
        List<House> downtownHouses = houseService.searchSimilarHouses("downtown", 3);
        assertNotNull(downtownHouses);

        System.out.println("Found " + downtownHouses.size() + " downtown houses:");
        for (House house : downtownHouses) {
            System.out.println("- " + house.getAddress() + ", " + house.getCity() + " (Neighborhood: "
                    + house.getNeighborhood() + ")");
        }
    }
}

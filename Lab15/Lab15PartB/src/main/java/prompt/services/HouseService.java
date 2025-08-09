package prompt.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import prompt.domains.House;

@Service
public class HouseService {

    private final List<House> houses = new ArrayList<>();

    public HouseService() {
        // Initialize with sample data
        populateSampleData();
    }

    public void saveHouse(House house) {
        houses.add(house);
    }

    public void saveHouses(List<House> houses) {
        this.houses.addAll(houses);
    }

    public List<House> searchSimilarHouses(String query, int maxResults) {
        // Simple keyword-based search for now
        String lowerQuery = query.toLowerCase();

        return houses.stream()
                .filter(house -> {
                    String houseText = house.toString().toLowerCase();
                    return houseText.contains(lowerQuery) ||
                            house.getDescription() != null && house.getDescription().toLowerCase().contains(lowerQuery)
                            ||
                            house.getAmenities() != null && house.getAmenities().toLowerCase().contains(lowerQuery) ||
                            house.getNeighborhood() != null
                                    && house.getNeighborhood().toLowerCase().contains(lowerQuery);
                })
                .limit(maxResults)
                .collect(Collectors.toList());
    }

    public void populateSampleData() {
        houses.clear();

        // Sample rental houses
        houses.add(new House("1", "123 Main St", "Boston", "MA", "02101",
                new BigDecimal("2500"), 2, 1, 1200.0, "apartment",
                true, false, true, true, true,
                "Modern apartment in downtown Boston with great amenities",
                "Dishwasher, Central AC, Hardwood floors", "Downtown", 2015, "John Smith - 555-0101"));

        houses.add(new House("2", "456 Oak Ave", "Cambridge", "MA", "02139",
                new BigDecimal("3200"), 3, 2, 1800.0, "house",
                true, false, false, true, false,
                "Charming house near Harvard Square with backyard",
                "Backyard, Garage, Fireplace", "Harvard Square", 1920, "Mary Johnson - 555-0202"));

        houses.add(new House("3", "789 Pine St", "Somerville", "MA", "02144",
                new BigDecimal("1800"), 1, 1, 800.0, "apartment",
                false, false, false, true, true,
                "Cozy studio apartment perfect for students",
                "In-unit laundry, Bike storage", "Davis Square", 2000, "Bob Wilson - 555-0303"));

        houses.add(new House("4", "321 Elm St", "Brookline", "MA", "02445",
                new BigDecimal("4500"), 4, 3, 2500.0, "house",
                true, true, true, true, true,
                "Luxury family home with pool and gym access",
                "Pool, Gym, Garden, Smart home features", "Coolidge Corner", 2018, "Sarah Davis - 555-0404"));

        houses.add(new House("5", "654 Maple Dr", "Medford", "MA", "02155",
                new BigDecimal("2200"), 2, 2, 1400.0, "townhouse",
                true, false, false, true, false,
                "Modern townhouse with attached garage",
                "Garage, Deck, Central AC", "Mystic Valley", 2010, "Mike Brown - 555-0505"));

        houses.add(new House("6", "987 Cedar Ln", "Arlington", "MA", "02474",
                new BigDecimal("2800"), 3, 2, 1600.0, "house",
                true, false, true, true, true,
                "Family-friendly house near parks and schools",
                "Fenced yard, Basement, Fireplace", "Arlington Heights", 1995, "Lisa Chen - 555-0606"));

        houses.add(new House("7", "147 Birch Rd", "Watertown", "MA", "02472",
                new BigDecimal("1900"), 1, 1, 900.0, "apartment",
                false, false, false, true, true,
                "Pet-friendly apartment with easy access to public transit",
                "Pet park nearby, Bike storage", "East Watertown", 2005, "Tom Anderson - 555-0707"));

        houses.add(new House("8", "258 Spruce Ave", "Belmont", "MA", "02478",
                new BigDecimal("3800"), 4, 3, 2200.0, "house",
                true, true, true, true, false,
                "Elegant home with pool and tennis court access",
                "Pool, Tennis court, Wine cellar", "Belmont Hill", 2012, "Jennifer Lee - 555-0808"));

        houses.add(new House("9", "369 Willow Way", "Newton", "MA", "02458",
                new BigDecimal("2900"), 3, 2, 1700.0, "house",
                true, false, false, true, true,
                "Charming house with large backyard and deck",
                "Deck, Garden, Fireplace", "Newton Centre", 1988, "David Miller - 555-0909"));

        houses.add(new House("10", "741 Ash Blvd", "Waltham", "MA", "02453",
                new BigDecimal("2100"), 2, 1, 1100.0, "apartment",
                true, false, true, true, false,
                "Modern apartment with gym access and parking",
                "Gym, Parking, Rooftop deck", "Moody Street", 2016, "Rachel Green - 555-1010"));
    }
}

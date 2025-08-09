package prompt.domains;

import java.math.BigDecimal;

import org.springframework.ai.document.Document;
import org.springframework.data.annotation.Id;

public class House {
    @Id
    private String id;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private BigDecimal rent;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double squareFeet;
    private String propertyType; // apartment, house, condo, townhouse
    private Boolean hasParking;
    private Boolean hasPool;
    private Boolean hasGym;
    private Boolean hasWifi;
    private Boolean isPetFriendly;
    private String description;
    private String amenities;
    private String neighborhood;
    private Integer yearBuilt;
    private String contactInfo;

    // Default constructor
    public House() {
    }

    // Constructor with all fields
    public House(String id, String address, String city, String state, String zipCode,
            BigDecimal rent, Integer bedrooms, Integer bathrooms, Double squareFeet,
            String propertyType, Boolean hasParking, Boolean hasPool, Boolean hasGym,
            Boolean hasWifi, Boolean isPetFriendly, String description, String amenities,
            String neighborhood, Integer yearBuilt, String contactInfo) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.rent = rent;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFeet = squareFeet;
        this.propertyType = propertyType;
        this.hasParking = hasParking;
        this.hasPool = hasPool;
        this.hasGym = hasGym;
        this.hasWifi = hasWifi;
        this.isPetFriendly = isPetFriendly;
        this.description = description;
        this.amenities = amenities;
        this.neighborhood = neighborhood;
        this.yearBuilt = yearBuilt;
        this.contactInfo = contactInfo;
    }

    // Method to convert House to Document for vector store
    public Document toDocument() {
        String content = String.format(
                "House at %s, %s, %s %s. %s bedroom, %s bathroom %s with %s sq ft. " +
                        "Rent: $%s/month. %s. %s. %s. %s. %s. %s. %s. Contact: %s",
                address, city, state, zipCode,
                bedrooms, bathrooms, propertyType, squareFeet,
                rent,
                hasParking ? "Has parking" : "No parking",
                hasPool ? "Has pool" : "No pool",
                hasGym ? "Has gym" : "No gym",
                hasWifi ? "Has wifi" : "No wifi",
                isPetFriendly ? "Pet friendly" : "No pets",
                description != null ? description : "",
                amenities != null ? "Amenities: " + amenities : "",
                neighborhood != null ? "Neighborhood: " + neighborhood : "",
                contactInfo);

        return new Document(content, getMetadata());
    }

    private java.util.Map<String, Object> getMetadata() {
        java.util.Map<String, Object> metadata = new java.util.HashMap<>();
        metadata.put("id", id);
        metadata.put("address", address);
        metadata.put("city", city);
        metadata.put("state", state);
        metadata.put("zipCode", zipCode);
        metadata.put("rent", rent);
        metadata.put("bedrooms", bedrooms);
        metadata.put("bathrooms", bathrooms);
        metadata.put("squareFeet", squareFeet);
        metadata.put("propertyType", propertyType);
        metadata.put("hasParking", hasParking);
        metadata.put("hasPool", hasPool);
        metadata.put("hasGym", hasGym);
        metadata.put("hasWifi", hasWifi);
        metadata.put("isPetFriendly", isPetFriendly);
        metadata.put("description", description);
        metadata.put("amenities", amenities);
        metadata.put("neighborhood", neighborhood);
        metadata.put("yearBuilt", yearBuilt);
        metadata.put("contactInfo", contactInfo);
        return metadata;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean getHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public Boolean getHasGym() {
        return hasGym;
    }

    public void setHasGym(Boolean hasGym) {
        this.hasGym = hasGym;
    }

    public Boolean getHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public Boolean getIsPetFriendly() {
        return isPetFriendly;
    }

    public void setIsPetFriendly(Boolean isPetFriendly) {
        this.isPetFriendly = isPetFriendly;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", rent=" + rent +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", squareFeet=" + squareFeet +
                ", propertyType='" + propertyType + '\'' +
                ", hasParking=" + hasParking +
                ", hasPool=" + hasPool +
                ", hasGym=" + hasGym +
                ", hasWifi=" + hasWifi +
                ", isPetFriendly=" + isPetFriendly +
                ", description='" + description + '\'' +
                ", amenities='" + amenities + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", yearBuilt=" + yearBuilt +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}

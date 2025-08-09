# Rental House Search Application

This is a semantic search application for rental houses built with Spring Boot. The application allows users to search for rental houses based on natural language queries and returns the most relevant matches.

## Features

- **Semantic Search**: Search for houses using natural language queries
- **REST API**: Clean REST interface for searching houses
- **Web Interface**: Beautiful HTML interface for easy testing
- **Sample Data**: Pre-populated with 10 sample rental houses
- **Configurable Results**: Specify how many houses to return in search results

## API Endpoints

### Search Houses

```
GET /api/houses/search?query={search_query}&maxResults={number}
```

**Parameters:**

- `query` (required): Natural language search query (e.g., "pet friendly", "pool", "downtown", "2 bedroom")
- `maxResults` (optional): Maximum number of houses to return (default: 5)

**Example:**

```
GET /api/houses/search?query=pet%20friendly&maxResults=3
```

### Populate Sample Data

```
POST /api/houses/populate
```

This endpoint populates the database with sample rental house data.

## Sample House Data

The application includes 10 sample rental houses with the following features:

- **Locations**: Boston, Cambridge, Somerville, Brookline, Medford, Arlington, Watertown, Belmont, Newton, Waltham
- **Property Types**: Apartments, houses, townhouses
- **Amenities**: Parking, pools, gyms, WiFi, pet-friendly options
- **Price Range**: $1,800 - $4,500/month
- **Bedrooms**: 1-4 bedrooms
- **Square Footage**: 800-2,500 sq ft

## Search Examples

Try these search queries:

- `pet friendly` - Find houses that allow pets
- `pool` - Find houses with swimming pools
- `downtown` - Find houses in downtown areas
- `2 bedroom` - Find 2-bedroom properties
- `gym` - Find houses with gym access
- `parking` - Find houses with parking
- `luxury` - Find luxury properties
- `student` - Find student-friendly properties
- `family` - Find family-friendly properties
- `modern` - Find modern properties

## Running the Application

1. **Prerequisites:**

   - Java 21
   - Maven
   - Redis (optional - currently using in-memory storage)

2. **Start the application:**

   ```bash
   mvn spring-boot:run
   ```

3. **Access the web interface:**

   - Open your browser and go to `http://localhost:8080`
   - The application will automatically load sample data and perform an initial search

4. **Use the REST API directly:**

   ```bash
   # Search for pet-friendly houses
   curl "http://localhost:8080/api/houses/search?query=pet%20friendly&maxResults=3"

   # Populate sample data
   curl -X POST "http://localhost:8080/api/houses/populate"
   ```

## Technical Details

### Architecture

- **Spring Boot 3.5.0**: Main framework
- **Spring Web**: REST API support
- **In-Memory Storage**: Currently using ArrayList for simplicity
- **HTML/CSS/JavaScript**: Web interface

### House Model

Each house includes:

- Basic info: address, city, state, zip code
- Property details: bedrooms, bathrooms, square footage, property type
- Amenities: parking, pool, gym, WiFi, pet-friendly
- Additional info: description, amenities list, neighborhood, year built, contact info

### Search Algorithm

The current implementation uses keyword-based search that matches against:

- House description
- Amenities
- Neighborhood
- All house properties (converted to string)

## Future Enhancements

To implement true semantic search with Redis vector store:

1. **Add Redis Vector Store Dependencies:**

   ```xml
   <dependency>
       <groupId>org.springframework.ai</groupId>
       <artifactId>spring-ai-starter-vector-store-redis</artifactId>
   </dependency>
   ```

2. **Configure Embedding Model:**

   - Add embedding client configuration
   - Configure vector dimensions and similarity metrics

3. **Update Search Logic:**
   - Convert text queries to embeddings
   - Perform vector similarity search
   - Return semantically similar results

## Project Structure

```
src/main/java/prompt/
├── controllers/
│   └── HouseSearchController.java    # REST API endpoints
├── domains/
│   └── House.java                   # House entity model
├── services/
│   └── HouseService.java            # Business logic and search
└── SimplePromptApplication.java     # Main application class

src/main/resources/
├── static/
│   └── index.html                   # Web interface
└── application.properties           # Configuration
```

## Testing

1. **Web Interface Testing:**

   - Open `http://localhost:8080`
   - Try different search queries
   - Test the "Populate Data" button

2. **API Testing:**

   ```bash
   # Test search endpoint
   curl "http://localhost:8080/api/houses/search?query=pool&maxResults=2"

   # Test populate endpoint
   curl -X POST "http://localhost:8080/api/houses/populate"
   ```

## Notes

- The current implementation uses in-memory storage for simplicity
- To implement true semantic search, you would need to integrate with Redis and an embedding model
- The application is designed to be easily extensible for vector store integration
- Sample data includes diverse properties to demonstrate search functionality

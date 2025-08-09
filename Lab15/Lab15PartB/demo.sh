#!/bin/bash

echo "🏠 Rental House Search Application Demo"
echo "======================================"
echo ""

# Check if the application is running
echo "Checking if application is running..."
if curl -s http://localhost:8080/api/houses/search?query=test > /dev/null 2>&1; then
    echo "✅ Application is running on http://localhost:8080"
else
    echo "❌ Application is not running. Please start it with: ./mvnw spring-boot:run"
    echo ""
    echo "To start the application:"
    echo "1. Make sure you have Java 21 installed"
    echo "2. Run: ./mvnw spring-boot:run"
    echo "3. Wait for the application to start"
    echo "4. Then run this demo script again"
    exit 1
fi

echo ""
echo "📋 Testing REST API endpoints..."
echo ""

# Test 1: Search for pet-friendly houses
echo "1. Searching for pet-friendly houses (max 3 results):"
curl -s "http://localhost:8080/api/houses/search?query=pet%20friendly&maxResults=3" | jq '.[] | {address: .address, city: .city, rent: .rent, petFriendly: .isPetFriendly}' 2>/dev/null || echo "Response received (jq not available for pretty printing)"

echo ""
echo ""

# Test 2: Search for houses with pools
echo "2. Searching for houses with pools (max 2 results):"
curl -s "http://localhost:8080/api/houses/search?query=pool&maxResults=2" | jq '.[] | {address: .address, city: .city, rent: .rent, hasPool: .hasPool}' 2>/dev/null || echo "Response received (jq not available for pretty printing)"

echo ""
echo ""

# Test 3: Search for downtown properties
echo "3. Searching for downtown properties (max 3 results):"
curl -s "http://localhost:8080/api/houses/search?query=downtown&maxResults=3" | jq '.[] | {address: .address, city: .city, rent: .rent, neighborhood: .neighborhood}' 2>/dev/null || echo "Response received (jq not available for pretty printing)"

echo ""
echo ""

# Test 4: Search for 2-bedroom properties
echo "4. Searching for 2-bedroom properties (max 3 results):"
curl -s "http://localhost:8080/api/houses/search?query=2%20bedroom&maxResults=3" | jq '.[] | {address: .address, city: .city, rent: .rent, bedrooms: .bedrooms}' 2>/dev/null || echo "Response received (jq not available for pretty printing)"

echo ""
echo ""

# Test 5: Populate sample data
echo "5. Testing populate endpoint:"
curl -s -X POST "http://localhost:8080/api/houses/populate"

echo ""
echo ""
echo "🌐 Web Interface"
echo "==============="
echo "You can also access the web interface at: http://localhost:8080"
echo ""
echo "The web interface provides:"
echo "- A search form for natural language queries"
echo "- Configurable number of results"
echo "- Beautiful display of house details"
echo "- Easy testing of different search terms"
echo ""
echo "Try these search queries in the web interface:"
echo "- pet friendly"
echo "- pool"
echo "- downtown"
echo "- 2 bedroom"
echo "- gym"
echo "- parking"
echo "- luxury"
echo "- student"
echo "- family"
echo "- modern"
echo ""
echo "🎯 API Endpoints Summary"
echo "======================="
echo "GET  /api/houses/search?query={query}&maxResults={number} - Search for houses"
echo "POST /api/houses/populate - Populate sample data"
echo ""
echo "Demo completed! 🎉"

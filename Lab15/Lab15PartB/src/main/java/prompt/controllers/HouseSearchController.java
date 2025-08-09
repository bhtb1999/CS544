package prompt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prompt.domains.House;
import prompt.services.HouseService;

@RestController
@RequestMapping("/api/houses")
public class HouseSearchController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/search")
    public List<House> searchHouses(@RequestParam String query,
            @RequestParam(defaultValue = "5") int maxResults) {
        return houseService.searchSimilarHouses(query, maxResults);
    }

    @PostMapping("/populate")
    public String populateHouses() {
        houseService.populateSampleData();
        return "Sample houses have been added to the search database";
    }
}

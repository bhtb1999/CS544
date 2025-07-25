package domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany
    @OrderColumn(name = "flight_order")
    private List<Flight> flights = new ArrayList<Flight>();

    protected Passenger() {
    }

    public Passenger(String name, List<Flight> flights) {
        super();
        if(flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.flights = flights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}



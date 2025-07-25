package domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private int id;
    private int flightNumber;
    @Column(name = "departure_from")
    private String from;
    @Column(name = "arrival_to")
    private String to;
    private Date date;

    protected Flight() {
    }

    public Flight(int flightNumber, String from, String to, Date date) {
        super();
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "date=" + date +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", flightNumber=" + flightNumber +
                '}';
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}



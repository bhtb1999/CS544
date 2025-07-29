package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;

    private Date date;
    private String description;

    public TraceRecord() {}

    public TraceRecord(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TraceRecord{" +
                "date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}

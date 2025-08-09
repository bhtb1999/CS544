package prompt.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Profit {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String company;
    private String description;
    private Date date;
    private int amount;

    public Profit() {}

    public Profit(String name,String company, String description, Date date, int amount) {
        this.name = name;
        this.company = company;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Profit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

package info.upump.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "autonumber")
public class AutoNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @Column(unique = true)
    @NotBlank(message = "поле не должно быть пустым")
    private String number;

    @NotBlank(message = "поле не должно быть пустым")
    private String description;

    public AutoNumber() {
    }

    public AutoNumber(String number, String description) {
        this.number = number;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AutoNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

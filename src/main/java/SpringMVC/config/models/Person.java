package SpringMVC.config.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name can`t be empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;
    @OneToMany(mappedBy = "owner")
    private List<Item> Items;
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email should be valid!")
    @Column(name = "email")
    private String email;
    // Country, City, Postal Code ( 6 digits)
//        @Column(name = "address")
//        @Pattern(regexp = "[A-Z]\\w+,[A-Z]\\w+,\\d{6}", message = "Your address should be in this format: Country, City, Postal Code(6 digits)")
//        private String address;
    public Person()
    {

    }
    public Person(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
//        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

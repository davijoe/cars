package dat3.car.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends AdminDetails {

    @Id
    @Column(nullable = false, length = 45, unique = true)
    private String username;

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column(length = 65)
    private String street;

    @Column(length = 45)
    private String city;

    @Column(length = 10)
    private String zip;

    @Column(nullable = false)
    private boolean approved;

    private int ranking;

    public Member(String username, String email, String password,
                  String firstName, String lastName, String street,
                  String city, String zip){
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public void addRole(int user) {
    }
}

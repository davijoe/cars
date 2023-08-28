package dat3.car.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45)
    String username;

    @Column(nullable = false, length = 45)
    String email;

    @Column(nullable = false, length = 45)
    String password;

    @Column(nullable = false, length = 45)
    String firstName;

    @Column(nullable = false, length = 45)
    String lastName;

    @Column(length = 65)
    String street;

    @Column(length = 45)
    String city;

    @Column(length = 10)
    int zip;

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip){
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}

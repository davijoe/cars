package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {

    @Column(length = 45)
    @NotNull
    private String firstName;

    @Column(length = 45)
    @NotNull
    private String lastName;

    @Column(length = 65)
    private String street;

    @Column(length = 45)
    private String city;

    @Column(length = 10)
    private String zip;

    private boolean approved;

    private int ranking;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<Reservation> reservations = new ArrayList<>();

    public Member(String username, String email, String password,
                  String firstName, String lastName, String street,
                  String city, String zip){
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
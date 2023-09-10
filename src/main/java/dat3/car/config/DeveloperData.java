package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;


    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Running DeveloperData");

        List<Car> cars = new ArrayList<>();
        //Devdata MED max discount
        cars.add(new Car("Acura","Integra",1504.41,168));
        cars.add(new Car("Alfa Romeo","164",1577.65,140));
        cars.add(new Car("Audi","100",831.09,380));
        cars.add(new Car("Audi","A3 Sportback e-tron",1006.49,269));
        cars.add(new Car("Bentley","Arnage",819.89,349));
        cars.add(new Car("BMW","1 Series",321.96,324));
        cars.add(new Car("BMW","3 Series",933.95,185));
        cars.add(new Car("BMW","6 Series",1295.01,395));
        cars.add(new Car("Cadillac","ATS",896.64,234));
        cars.add(new Car("Chevrolet","1500 Extended Cab",635.79,294));
        cars.add(new Car("Chevrolet","2500 HD Regular Cab",1517.11,130));
        cars.add(new Car("Chevrolet","G-Series G20",1453.1,381));
        cars.add(new Car("Chrysler","200",1364.89,382));
        cars.add(new Car("Dodge","Intrepid",1686.07,117));
        cars.add(new Car("Ferrari","458 Speciale",1078.59,367));
        cars.add(new Car("Ferrari","599 GTB Fiorano",1681.3,379));
        cars.add(new Car("FIAT","124 Spider",1481.75,390));
        cars.add(new Car("Ford","Aerostar Cargo",1105.81,294));
        cars.add(new Car("Ford","Aerostar Passenger",665.97,230));
        cars.add(new Car("Ford","EcoSport",1292.42,213));
        cars.add(new Car("GMC","1500 Club Coupe",805.96,363));
        cars.add(new Car("Honda","Accord",1412.15,157));
        cars.add(new Car("HUMMER","H2",1326.06,246));
        cars.add(new Car("Hyundai","Accent",759.26,259));
        cars.add(new Car("Kia","Amanti",672.13,397));
        cars.add(new Car("Lincoln","MKT",1369.64,167));
        cars.add(new Car("Maybach","62",1028.57,185));
        cars.add(new Car("MAZDA","323",1161.74,293));
        cars.add(new Car("McLaren","570S",1375.84,399));
        cars.add(new Car("Mercedes-Benz","190 E",429.07,221));
        cars.add(new Car("MINI","Cooper",706.26,117));
        cars.add(new Car("Mitsubishi","3000GT",1235.55,201));
        cars.add(new Car("Mitsubishi","Mirage G4",1333.3,346));
        cars.add(new Car("Nissan","200SX",1678.17,103));
        cars.add(new Car("Nissan","Altima",1579.58,188));
        cars.add(new Car("Oldsmobile","88",1120.97,314));
        cars.add(new Car("Plymouth","Acclaim",1542.01,353));
        cars.add(new Car("Polestar","2",718.98,313));
        cars.add(new Car("Porsche","718 Cayman",604.89,173));
        cars.add(new Car("Porsche","718 Spyder",690.23,120));
        cars.add(new Car("Porsche","Macan",1298.55,349));
        cars.add(new Car("Ram","1500 Classic Crew Cab",488.84,317));
        cars.add(new Car("Saab","3-Sep",609.13,273));
        cars.add(new Car("Subaru","Ascent",536.11,371));
        cars.add(new Car("Suzuki","Aerio",1246.75,165));
        cars.add(new Car("Toyota","4Runner",423.57,302));
        cars.add(new Car("Toyota","Corolla",1513.09,294));
        cars.add(new Car("Volkswagen","Arteon",959.83,265));
        cars.add(new Car("Volkswagen","Golf",659.39,321));
        cars.add(new Car("Volvo","240",798.92,257));
        carRepository.saveAll(cars);

        System.out.println("Successfully saved all cars");

        List<Member> members = new ArrayList<>();
        members.add(new Member("member1", "m1@a.com", "letmein1", "Anders", "Andersen","Eksempelvænget 1", "Eksempelby","1234"));
        members.add(new Member("member2", "m2@a.com", "letmein2", "Bent", "Bentsen","Eksempelvænget 2", "Eksempelby","1234"));
        memberRepository.saveAll(members);
        System.out.println("Successfully saved all members");

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(LocalDate.of(2021, 11, 21), cars.get(0), members.get(0)));
        reservations.add(new Reservation(LocalDate.of(2021, 11, 22), cars.get(1), members.get(0)));
        reservationRepository.saveAll(reservations);
        System.out.println("Successfully saved all reservations");


        setupUserWithRoles();
    }

    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";
    private void setupUserWithRoles() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");

        UserWithRoles user1 = new UserWithRoles("user1", "passwordUsedByAll", "user1@a.com");
        UserWithRoles user2 = new UserWithRoles("user2", "passwordUsedByAll", "user2@a.com");
        UserWithRoles user3 = new UserWithRoles("user3", "passwordUsedByAll", "user3@a.com");
        UserWithRoles user4 = new UserWithRoles("user4", "passwordUsedByAll", "user4@a.com");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}

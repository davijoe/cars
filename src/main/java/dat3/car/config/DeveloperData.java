package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;

    final MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();

        //DevData uden max discount
        /*
        cars.add(new Car("Acura","Integra",1504.41));
        cars.add(new Car("Alfa Romeo","164",1577.65));
        cars.add(new Car("Audi","100",831.09));
        cars.add(new Car("Audi","A3 Sportback e-tron",1006.49));
        cars.add(new Car("Bentley","Arnage",819.89));
        cars.add(new Car("BMW","1 Series",321.96));
        cars.add(new Car("BMW","3 Series",933.95));
        cars.add(new Car("BMW","6 Series",1295.01));
        cars.add(new Car("Cadillac","ATS",896.64));
        cars.add(new Car("Chevrolet","1500 Extended Cab",635.79));
        cars.add(new Car("Chevrolet","2500 HD Regular Cab",1517.11));
        cars.add(new Car("Chevrolet","G-Series G20",1453.1));
        cars.add(new Car("Chrysler","200",1364.89));
        cars.add(new Car("Dodge","Intrepid",1686.07));
        cars.add(new Car("Ferrari","458 Speciale",1078.59));
        cars.add(new Car("Ferrari","599 GTB Fiorano",1681.3));
        cars.add(new Car("FIAT","124 Spider",1481.75));
        cars.add(new Car("Ford","Aerostar Cargo",1105.81));
        cars.add(new Car("Ford","Aerostar Passenger",665.97));
        cars.add(new Car("Ford","EcoSport",1292.42));
        cars.add(new Car("GMC","1500 Club Coupe",805.96));
        cars.add(new Car("Honda","Accord",1412.15));
        cars.add(new Car("HUMMER","H2",1326.06));
        cars.add(new Car("Hyundai","Accent",759.26));
        cars.add(new Car("Kia","Amanti",672.13));
        cars.add(new Car("Lincoln","MKT",1369.64));
        cars.add(new Car("Maybach","62",1028.57));
        cars.add(new Car("MAZDA","323",1161.74));
        cars.add(new Car("McLaren","570S",1375.84));
        cars.add(new Car("Mercedes-Benz","190 E",429.07));
        cars.add(new Car("MINI","Cooper",706.26));
        cars.add(new Car("Mitsubishi","3000GT",1235.55));
        cars.add(new Car("Mitsubishi","Mirage G4",1333.3));
        cars.add(new Car("Nissan","200SX",1678.17));
        cars.add(new Car("Nissan","Altima",1579.58));
        cars.add(new Car("Oldsmobile","88",1120.97));
        cars.add(new Car("Plymouth","Acclaim",1542.01));
        cars.add(new Car("Polestar","2",718.98));
        cars.add(new Car("Porsche","718 Cayman",604.89));
        cars.add(new Car("Porsche","718 Spyder",690.23));
        cars.add(new Car("Porsche","Macan",1298.55));
        cars.add(new Car("Ram","1500 Classic Crew Cab",488.84));
        cars.add(new Car("Saab","3-Sep",609.13));
        cars.add(new Car("Subaru","Ascent",536.11));
        cars.add(new Car("Suzuki","Aerio",1246.75));
        cars.add(new Car("Toyota","4Runner",423.57));
        cars.add(new Car("Toyota","Corolla",1513.09));
        cars.add(new Car("Volkswagen","Arteon",959.83));
        cars.add(new Car("Volkswagen","Golf",659.39));
        cars.add(new Car("Volvo","240",798.92));
        */

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
        members.add(new Member("user1", "example1@example.com", "letmein", "Anders", "Andersen","Eksempelvænget 1", "Eksempelby","1234"));

        memberRepository.saveAll(members);

        System.out.println("Successfully saved all members");
    }
}

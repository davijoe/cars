package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CarServiceH2Test {

    @Autowired
    CarRepository carRepository;
    CarService carService;

    Car c1, c2, c3;  //Why don't we to add "isInitialize flag" here?

    @BeforeEach
    void setUp() {
        //Set up mock database with two members before each test
        c1 = carRepository.save(new Car("Toyota", "Yaris", 100.0, 10));
        c2 = carRepository.save(new Car("Skoda", "Octavia", 200.0, 20));
        c3 = carRepository.save(new Car("Volvo", "V60", 300.0, 30));
        carService = new CarService(carRepository); //Inject the mock repository
    }

    @Test
    void testGetCarsAllDetails() {
        List<CarResponse> carResponses = carService.getCars(true);
        assertEquals(3, carResponses.size(),"Expects three cars in the list");
        LocalDateTime time = carResponses.get(0).getCreated();
        assertNotNull(time,"Expects dates to be set when includeAll is true");
        System.out.println(time); //TODO: Fix this test
    }


}

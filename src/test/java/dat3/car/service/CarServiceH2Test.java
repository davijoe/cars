package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.util.TypeUtils.type;

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
    }

    @Test
    void testGetCarsNoDetails() {
        List<CarResponse> carResponses = carService.getCars(false);
        LocalDateTime time = carResponses.get(0).getCreated();
        assertNull(time,"Should be null when includeAll is false");
    }

    @Test
    void testFindByIdFound() {
        CarResponse res = carService.findById(1);
        assertEquals("Toyota",res.getBrand());
        assertEquals("Yaris",res.getModel());
        System.out.println(res.getCreated());
    }

    @Test
    void testFindByIdNotFound() {
        //Tests that ResponseStatus exception is thrown with status = 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> carService.findById(666));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        System.out.println(ex.getStatusCode() + ": Reason:" + ex.getReason());
    }

    @Test
    void testAddCar_CarDoesNotExist(){
        CarRequest req = CarRequest.builder()
                .brand("VW")
                .model("Polo")
                .pricePrDay(110.0)
                .bestDiscount(11)
                .build();

        //addCar saves a Car entity to the database
        CarResponse res = carService.addCar(req);
        assertEquals("VW",res.getBrand());
        assertTrue(carRepository.existsById(res.getId())); //Check that the member is actually saved to the database
    }

    @Test
    void testAddCar_CarAlreadyExist(){
        CarRequest request = new CarRequest();
        request.setBrand("Toyota");
        request.setModel("Yaris");

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> carService.addCar(request));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        System.out.println(ex.getStatusCode() + " - Reason: " + ex.getReason());
    }

    @Test
    void testEditCarWithExistingId(){
        //Change Car c1 from Toyota Yaris to VW Polo
        CarRequest request = new CarRequest(c1);
        request.setBrand("VW");
        request.setModel("Polo");

        carService.editCar(request, 1);
        CarResponse res = carService.findById(1);

        assertEquals("VW", res.getBrand());
        assertEquals("Polo", res.getModel());
    }

    @Test
    void testEditCarWithNonExistingId(){
        //Change Car c1 from Toyota Yaris to VW Polo
        CarRequest request = new CarRequest(c1);
        request.setId(42);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> carService.editCar(request, 42));

        System.out.println(ex.getStatusCode() + " - Reason: " + ex.getReason());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

}
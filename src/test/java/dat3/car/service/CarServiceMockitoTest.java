package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
   private CarService carService;

   @Mock
   private CarRepository carRepository;

   @BeforeEach
   void setUp() {
      carService = new CarService(carRepository);
   }
   private Car makeCar(String brand, String model, double pricePrDay, int bestDiscount){
      Car car = new Car(brand, model, pricePrDay, bestDiscount);
      car.setCreated(LocalDateTime.now());
      car.setEdited(LocalDateTime.now());
      return car;
   }

   @Test
   void getCars() {
      Car car1 = makeCar("b1", "m1", 100, 10); //mock data
      Car car2 = makeCar("b2", "m2", 200, 20); //mock data
      when(carRepository.findAll()).thenReturn(List.of(car1, car2)); //mock behavior
      List<CarResponse> responses = carService.getCars(true); //call the method
      assertEquals(2, responses.size()); //verify amount of cars in the response is 2
      assertNotNull(responses.get(0).getCreated()); //verify that the created date is set
   }

   @Test
   void findById() {
      when(carRepository.findById(1)).thenReturn(
              Optional.of(makeCar("b1", "m1", 100, 10))); //mock behavior of the repository
      CarResponse response = carService.findById(1); //call the method
      assertEquals("b1", response.getBrand()); //verify that brand is "b1"
   }

   @Test
   void addCar() {
      //Test data and mock behavior
      Car newCar = makeCar("b3", "m3", 300, 30); //mock data
      when(carRepository.save(any(Car.class))).thenReturn(newCar);
      //Call the method
      CarResponse response = carService.addCar(new CarRequest(newCar));
      //Verify the result
      assertEquals("b3", response.getBrand());
   }

   /*
   @Test
   void editCar() {

   }

   @Test
   void setBestDiscountForCar() {
   }

   @Test
   void deleteCarById() {
   }
   */

}
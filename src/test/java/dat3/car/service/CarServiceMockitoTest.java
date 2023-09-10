package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
      Car car1 = makeCar("b1", "m1", 100, 10);
      Car car2 = makeCar("b2", "m2", 200, 20);
      when(carRepository.findAll()).thenReturn(List.of(car1, car2));
      List<CarResponse> responses = carService.getCars(true);
      assertEquals(2, responses.size());
      assertNotNull(responses.get(0).getCreated());
   }

   @Test
   void findById() {
   }

   @Test
   void addCar() {
   }

   @Test
   void editCar() {
   }

   @Test
   void setBestDiscountForCar() {
   }

   @Test
   void deleteCarById() {
   }
}
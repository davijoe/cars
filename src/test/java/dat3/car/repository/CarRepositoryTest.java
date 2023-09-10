package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class CarRepositoryTest {


   CarService carService;
   @Mock
   CarRepository carRepository;

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
   void existsByBrandAndModel() {
      Car car1 = makeCar("b1", "m1", 100, 10); //mock data
      Car car2 = makeCar("b2", "m2", 200, 20); //mock data
      when(carRepository.existsByBrandAndModel("b1", "m1")).thenReturn(true); //mock behavior
      when(carRepository.existsByBrandAndModel("b2", "m2")).thenReturn(true); //mock behavior
      when(carRepository.existsByBrandAndModel("b2", "m1")).thenReturn(false); //mock behavior
      assertTrue(carRepository.existsByBrandAndModel("b1", "m1"));
      assertTrue(carRepository.existsByBrandAndModel("b2", "m2"));
      assertFalse(carRepository.existsByBrandAndModel("b2", "m1"));
   }

   @Test
   void findAllByBrand() {
      List<Car> cars = List.of(makeCar("b1", "m1", 100, 10), makeCar("b1", "m2", 200, 20));
      when(carRepository.findAllByBrand("b1")).thenReturn(cars);
      List<Car> carsByBrand = carRepository.findAllByBrand("b1");
      assertEquals(2, carsByBrand.size());
   }
}
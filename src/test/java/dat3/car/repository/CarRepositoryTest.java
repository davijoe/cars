package dat3.car.repository;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {


    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

}
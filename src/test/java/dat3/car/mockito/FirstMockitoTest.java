package dat3.car.mockito;

import dat3.car.repository.CarRepository;
import dat3.car.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstMockitoTest {

    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    public void test(){
        int num = 1;
        assertEquals(1, num);
    }


}

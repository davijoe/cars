package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

   @Autowired
   MemberRepository memberRepository;
   @Autowired
   CarRepository carRepository;
   @Autowired
   ReservationRepository reservationRepository;

   boolean isInitialized = false;
   @BeforeEach
   void setUp() {
      if(!isInitialized) return;
      memberRepository.deleteAll();
      Member m1 = memberRepository.save(new Member("member1", "m1@a.com", "letmein1", "Anders", "Andersen","Eksempelvænget 1", "Eksempelby","1234"));
      System.out.println("m1 = " + m1);
      Member m2 = memberRepository.save(new Member("member2", "m2@a.com", "letmein2", "Bent", "Bentsen","Eksempelvænget 2", "Eksempelby","1234"));
      System.out.println("m2 = " + m2);
      Car car1 = carRepository.save(new Car("Ford", "Mustang", 1000.0, 100));
      Reservation reservation1 = reservationRepository.save(new Reservation(LocalDate.of(2023, 11, 15), car1, m1));
      isInitialized = true;
   }

   @Test
   void deleteAll() {

      System.out.println("memberRepository.findAll().size() = " + memberRepository.findAll().size());

      memberRepository.deleteAll();
      assertEquals(0, memberRepository.findAll().size());
      assertEquals(0, memberRepository.count());
   }
}
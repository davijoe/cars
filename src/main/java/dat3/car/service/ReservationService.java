package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;
    MemberService memberService;

    public ReservationService(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
    }


    public ReservationResponse addReservation(ReservationRequest body) {
        if(body.getDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation date cannot be in the past");
        }
        if (reservationRepository.existsByRentalDateAndCarId(body.getDate(), body.getCarId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved for this date");
        }
        Member member = memberRepository.findById(body.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not found"));
        Car car = carRepository.findById(body.getCarId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));
        Reservation reservation = reservationRepository.save(new Reservation(body.getDate(),car,member));
        return new ReservationResponse(reservation);
    }



}

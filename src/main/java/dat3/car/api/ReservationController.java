package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    //Security --> Anonymous
    @GetMapping
    List<ReservationResponse> getReservations() {
        return service.getReservations();
    }

    @PostMapping
    public ReservationResponse makeReservation(@RequestBody ReservationRequest body) {
        return service.addReservation(body);
    }
}

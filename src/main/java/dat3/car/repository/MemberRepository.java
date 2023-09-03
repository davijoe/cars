package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findMemberByZip(String zip);

    Optional<Member> findMemberByEmail(String email);
}

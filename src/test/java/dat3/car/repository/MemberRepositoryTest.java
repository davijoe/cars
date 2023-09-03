package dat3.car.repository;

import dat3.car.entity.Member;
import org.h2.engine.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindByZip(){
        List<Member> members = memberRepository.findMemberByZip("2000");
        assertEquals(1, members.size());
    }

    /*
    @Test
    void setDateOfBirthTest(){
        Member m1 = memberRepository.findById("Bobby").get();
        assertEquals("1999, 01, 13", m1.getDateOfBirth());
    }
    */

}
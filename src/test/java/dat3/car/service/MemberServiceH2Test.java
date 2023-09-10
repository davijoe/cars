package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        //Set up mock database with two members before each test
        m1 = memberRepository.save(new Member("user01", "email01@abc.dk", "pw01", "fn01", "ln01",  "street01", "city01", "zip01"));
        m2 = memberRepository.save(new Member("user02", "email02@abc.dk", "pw02", "fn02", "ln02", "street02", "city02", "zip02"));
        memberService = new MemberService(memberRepository); //Inject the mock repository
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2, memberResponses.size(),"Expects two members in the list");
        LocalDateTime time = memberResponses.get(0).getCreated();
        //assertNotNull(time,"Expects dates to be set when includeAll is true");

        System.out.println(time); //TODO: Fix this test
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time,"Should be null when includeAll is false");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse res = memberService.findById("user01");
        assertEquals("user01", res.getUsername());
    }


    @Test
    void testFindByIdNotFound() {
    //Tests that ResponseStatus exception is thrown with status = 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> memberService.findById("NOT_ACTUALLY_A_USER"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }


    //MemberRequest come from API layer, and
    //MemberResponse is returned to the API layer
    @Test
    void testAddMember_UserDoesNotExist() {
        MemberRequest request = MemberRequest.builder().
                username("user03").
                email("usermail3@mail.dk").
                password("pw03").
                firstName("fn03").
                lastName("ln03").
                build();

        //addMember saves a Member entity to the database
        MemberResponse res = memberService.addMember(request);
        assertEquals("user03", res.getUsername());
        assertTrue(memberRepository.existsById("user03")); //Check that the member is actually saved to the database
    }


    @Test
    public void testAddMember_UserDoesExistThrows() {
        //Tests that ResponseStatus exception is thrown with status = 409 (BAD_REQUEST)
        MemberRequest request = new MemberRequest();
        request.setUsername("user01"); //This user already exists from our mock database / setUp() method

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> memberService.addMember(request));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("user01");
        request.setEmail("email01@abc.dk");
        request.setFirstName("GodtFornavn");
        request.setLastName("BedreEfternavn");

        memberService.editMember(request, "user01");
        MemberResponse response = memberService.findById("user01");

        assertEquals("user01", response.getUsername());
        assertEquals("email01@abc.dk", response.getEmail());
        assertEquals("GodtFornavn", response.getFirstName());
        assertEquals("BedreEfternavn", response.getLastName());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        MemberRequest res = new MemberRequest(m1);
        res.setUsername("blabla");

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> memberService.editMember(res, "iDontExist")); //username must not exist for the test to pass
        //Printing status and reason to the console
        System.out.println(ex.getStatusCode() + " - Explanation: " + ex.getReason());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        MemberRequest req = new MemberRequest(m1);
        req.setUsername("user03");

        //Tests that ResponseStatus exception is thrown with status = 400
        // (BAD_REQUEST) when trying to change primary key (username)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> memberService.editMember(req, "user01"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

        //prints the error status and reason to the console
        System.out.println(ex.getStatusCode() + " - Explanation: " + ex.getReason());
        assertEquals("Usernames cannot be changed", ex.getReason());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser("user01", 1);
        assertEquals(1,m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.setRankingForUser("UserThatDoesNotExist",1));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user01");
        assertFalse(memberRepository.existsById("user01"),"Should be false after deleting member");
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                   () -> memberService.deleteMemberByUsername("I dont exist"));
        //prints the error status and reason to the console
        System.out.println(ex.getStatusCode() + " - Explanation: " + ex.getReason());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
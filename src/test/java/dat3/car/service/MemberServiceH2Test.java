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
        m1 = memberRepository.save(new Member("user01", "email01@abc.dk", "pw01", "fn01", "ln01",  "street01", "city01", "zip01"));
        m2 = memberRepository.save(new Member("user02", "email02@abc.dk", "pw02", "fn02", "ln02", "street02", "city02", "zip02"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time,"Expects dates to be set when includeAll is true");
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
        assertEquals("user01",res.getUsername());
        assertEquals("email01@abc.dk",res.getEmail());
    }


    @Test
    void testFindByIdNotFound() {
    //Tests that ResponseStatus exception is thrown with status = 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> memberService.findById("NOT_ACTUALLY_A_USER"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
     * Internally addMember saves a Member entity to the database*/
    @Test
    void testAddMember_UserDoesNotExist() {
        MemberRequest request = MemberRequest.builder().
                username("user03").
                email("usermail3@mail.dk").
                password("pw03").
                firstName("fn03").
                lastName("ln03").
                build();
        MemberResponse res = memberService.addMember(request);
        assertEquals("user03", res.getUsername());
        assertTrue(memberRepository.existsById("user03")); //Check that the member is actually saved to the database
    }


    @Test
    public void testAddMember_UserDoesExistThrows() {
        //Tests that ResponseStatus exception is thrown with status = 409 (BAD_REQUEST)
        MemberRequest request = new MemberRequest();
        request.setUsername("user01"); //This user already exists from our mock database / setUp() method

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> memberService.addMember(request));

        //obs: Assertions.assertEquals instead of assertEquals because migration to JUnit5
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
        MemberRequest request = new MemberRequest();

        request.setUsername("user01");
        request.setEmail("email01@abc.dk");
        request.setFirstName("GodtFornavn");
        request.setLastName("BedreEfternavn");

        memberService.editMember(request, "user01");
        MemberResponse response = memberService.findById("user01");

        Assertions.assertEquals("user01", response.getUsername());
        Assertions.assertEquals("email01@abc.dk", response.getEmail());
        Assertions.assertEquals("GodtFornavn", response.getFirstName());
        Assertions.assertEquals("BedreEfternavn", response.getLastName());
    }
    @Test
    void testEditMemberWithExistingEmail() {
        //TODO
        MemberRequest request = new MemberRequest();
        MemberResponse response = memberService.findMemberByEmail("email01@abc.dk");

        //System.out.println(response.getUsername());

        request.setUsername("user01");
        request.setFirstName("GodtFornavn");
        request.setLastName("BedreEfternavn");
        memberService.editMember(request, "user01");

        //System.out.println(response.getEmail());

        Assertions.assertEquals("user01", response.getUsername());
    }


    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

//Under her resten

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
        //TODO
    }
}

package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Executable;
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
        // assertEquals(2,memberResponses.size(),"Expects 2 members");
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
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.findById("NonExistentUser"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());

        //TODO
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember saves a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {

        Member m3 = memberRepository.save(new Member("user03", "email03@abc.dk", "pw03", "fn03", "ln03", "street03", "city03", "zip03"));
        memberService = new MemberService(memberRepository);

        System.out.println(m3.getCity());

        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        //TODO
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        //TODO
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
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

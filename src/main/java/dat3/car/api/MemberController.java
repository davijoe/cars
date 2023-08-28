package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //Security ??? Admin access only (mangler)
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false);}

    //Security ???
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {return null;}

    //Security --> ??????
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body){
        return null;
    }

    //Security ???
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return null;
    }

    //Security ????
    @PatchMapping("/ranking/{username}/{value}")
    ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
        return null;
    }

    // Security ????
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {}

}
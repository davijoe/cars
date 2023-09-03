package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
//        List<MemberResponse> response = new ArrayList<>();
//        for (Member member : members){
//            MemberResponse mr = new MemberResponse(member, includeAll);
//            response.add(mr);
//        }
//        List<MemberResponse> response = members.stream().map(member -> new MemberResponse(member, includeAll)).toList();

//        return members.stream().map((member -> new MemberResponse(member,includeAll))).toList();
        return members.stream().map(MemberResponse::new).collect(Collectors.toList());
    }

    public MemberResponse findById(String username){
        Member member = getMemberByUsername(username);
        return new MemberResponse(member,true);
    }
    public MemberResponse findMemberByEmail(String email){
        Member member = getMemberByEmail(email);
        return new MemberResponse(member,true);
    }

    public MemberResponse addMember(MemberRequest body){
        if(memberRepository.existsById(body.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user already exists");
        }
        Member newMember = MemberRequest.getMemberEntity(body);
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, true);
    }

    public void editMember(MemberRequest body, String username){
        Member member = getMemberByUsername(username);
        if(!username.equals(body.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
    }

    public void setRankingForUser(String username, int value){
        Member member = getMemberByUsername(username);
        member.setRanking(value);
        memberRepository.save(member);
    }

    public void deleteMemberByUsername(String username){
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }

    private Member getMemberByUsername(String username){
        return memberRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this username exist"));
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this first name exist"));
    }










}

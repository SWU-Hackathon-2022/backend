package MOAI.moai.note.service;

import MOAI.moai.member.Member;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NoteServiceTest {


    @Autowired
    NoteService noteService;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void getAllNotesByMember() throws Exception {
        /**
         *  given : given condition
         */

        //noteService.getAllNotesByMember()


        /**
         *  when  : execution
         */



        /**
         *  then  : result
         */


    }

    @Test
    void getOneNoteDetail() {
    }

    @Test
    void sendArtistToComposerNote() {
    }

    @Test
    void sendAcceptToArtistNote() {
    }

    @Test
    void sendDeclineToArtistNote() {
    }
}
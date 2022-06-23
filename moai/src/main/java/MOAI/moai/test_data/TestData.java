package MOAI.moai.test_data;

import MOAI.moai.creation.Creation;
import MOAI.moai.creation.repository.CreationRepository;
import MOAI.moai.member.Member;
import MOAI.moai.member.MemberGenre;
import MOAI.moai.member.genre.Genre;
import MOAI.moai.member.repository.MemberGenreRepository;
import MOAI.moai.member.repository.MemberRepository;
import MOAI.moai.member.type.MemberType;
import MOAI.moai.music.Music;
import MOAI.moai.music.repository.MusicRepository;
import MOAI.moai.note.Note;
import MOAI.moai.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.File;

import static MOAI.moai.member.Member.createMember;
import static MOAI.moai.member.MemberGenre.createMemberGenre;
import static MOAI.moai.music.Music.createMusic;

/**
 *  Test Data (for test)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TestData {

    /*private final InitService initService;

    @PostConstruct
    public void init() { initService.init(); }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepository memberRepository;
        private final MusicRepository musicRepository;
        private final MemberGenreRepository memberGenreRepository;
        private final CreationRepository creationRepository;
        private final NoteRepository noteRepository;

        @Transactional
        public void init() {

            // member 추가
            Member member1 = createMember(MemberType.COMPOSER, "token1", "/member/thumbnail/memberThumbnail1.jpg", "김작곡닉네임");
            Member member2 = createMember(MemberType.COMPOSER, "token2", "/member/thumbnail/memberThumbnail2.jpg","이작곡닉네임");
            Member member3 = createMember(MemberType.ARTIST, "token3", "/member/thumbnail/memberThumbnail3.jpg","김아티스트닉네임");
            Member member4 = createMember(MemberType.ARTIST, "token4","/member/thumbnail/memberThumbnail4.jpg", "이아티스트닉네임");
            Member member5 = createMember(MemberType.ARTIST, "token5", "/member/thumbnail/memberThumbnail5.jpg","박아티스트닉네임");
            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);
            memberRepository.save(member4);
            memberRepository.save(member5);

            // memberGenre 추가
            MemberGenre memberGenre1 = createMemberGenre(member1, Genre.BALLAD);
            MemberGenre memberGenre2 = createMemberGenre(member1, Genre.ROCK);
            MemberGenre memberGenre3 = createMemberGenre(member2, Genre.POP);
            MemberGenre memberGenre4 = createMemberGenre(member2, Genre.R_B);
            MemberGenre memberGenre5 = createMemberGenre(member2, Genre.DISCO);

            memberGenreRepository.save(memberGenre1);
            memberGenreRepository.save(memberGenre2);
            memberGenreRepository.save(memberGenre3);
            memberGenreRepository.save(memberGenre4);
            memberGenreRepository.save(memberGenre5);


            // music 추가
            Music music1 = createMusic(member1, "노래이름1", Genre.BALLAD, "이것은 노래이름1 입니다!", "#발라드 #신남",
                    "/music/file/musicFile1.mp3",
                    "/music/thumbnail/musicThumbnail1.png");
            Music music2 = createMusic(member1, "노래이름2", Genre.POP, "이것은 노래이름2 입니다!", "#팝 #신남",
                    "/music/file/musicFile2.mp3",
                    "/music/thumbnail/musicThumbnail2.jpg");
            Music music3 = createMusic(member1, "노래이름3", Genre.ROCK, "이것은 노래이름3 입니다!", "#락 #신남",
                    "/music/file/musicFile3.mp3",
                    "/music/thumbnail/musicThumbnail3.png");
            Music music4 = createMusic(member2, "노래이름4", Genre.DANCE, "이것은 노래이름4 입니다!", "#댄스 #신남",
                    "/music/file/musicFile4.mp3",
                    "/music/thumbnail/musicThumbnail4.jpg");
            Music music5 = createMusic(member2, "노래이름5", Genre.DISCO, "이것은 노래이름5 입니다!", "#디스코 #신남",
                    "/music/file/musicFile5.mp3",
                    "/music/thumbnail/musicThumbnail5.jpg");

            musicRepository.save(music1);
            musicRepository.save(music2);
            musicRepository.save(music3);
            musicRepository.save(music4);
            musicRepository.save(music5);

            // Creation 추가
            Creation creation1 = Creation.createCreation(music1, member3, "영상이름 1",null );
            Creation creation2 = Creation.createCreation(music2, member3, "영상이름 2",null );
            Creation creation3 = Creation.createCreation(music3, member3, "영상이름 3",null );
            Creation creation4 = Creation.createCreation(music4, member3, "영상이름 4",null );
            Creation creation5 = Creation.createCreation(music5, member3, "영상이름 5",null );
            creationRepository.save(creation1);
            creationRepository.save(creation2);
            creationRepository.save(creation3);
            creationRepository.save(creation4);
            creationRepository.save(creation5);

            // Note 추가 (2차 창작자 -> 1차 창작자)
            Note note1 = Note.createArtistToComposerNote(music1, member3, "안녕하세요 제 이름은 김아티스트입니다. 같이 협업 할 수 있을까요. 이 음악을 사용할 수 있게 해주세요.");
            Note note2 = Note.createArtistToComposerNote(music1, member4, "안녕하세요 제 이름은 이아티스트입니다. 같이 협업 할 수 있을까요. 이 음악을 사용할 수 있게 해주세요.");
            Note note3 = Note.createArtistToComposerNote(music2, member5, "안녕하세요 제 이름은 박아티스트입니다. 같이 협업 할 수 있을까요. 이 음악을 사용할 수 있게 해주세요.");
            noteRepository.save(note1);
            noteRepository.save(note2);
            noteRepository.save(note3);
            // Note 추가 (1차 창작자 -> 2차 창작자 수락/거절)
            Note note4 = Note.createComposerToArtistAcceptNote(music1, member3, note1);
            Note note5 = Note.createComposerToArtistDeclineNote(music1, member4, note2);
            Note note6 = Note.createComposerToArtistDeclineNote(music2, member5, note3);
            noteRepository.save(note4);
            noteRepository.save(note5);
            noteRepository.save(note6);
        }
    }*/

}

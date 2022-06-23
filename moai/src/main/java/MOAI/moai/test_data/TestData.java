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

    private final InitService initService;

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
            Member member1 = createMember(MemberType.COMPOSER, "token1", "/member/thumbnail/memberThumbnail1.jpg", "피터");
            Member member2 = createMember(MemberType.COMPOSER, "token2", "/member/thumbnail/memberThumbnail2.jpg","필립");
            Member member3 = createMember(MemberType.ARTIST, "token3", "/member/thumbnail/memberThumbnail3.jpg","레이");
            Member member4 = createMember(MemberType.ARTIST, "token4","/member/thumbnail/memberThumbnail4.jpg", "리오");
            Member member5 = createMember(MemberType.ARTIST, "token5", "/member/thumbnail/memberThumbnail5.jpg","아샤");
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
            Music music1 = createMusic(member1, "All By Myself", Genre.BALLAD, "In music, the introduction is a passage or section which opens a movement or a separate piece", "#트렌디 #재즈",
                    "/music/file/musicFile1.mp3",
                    "/music/thumbnail/musicThumbnail1.png");
            Music music2 = createMusic(member1, "Goodbye Yellow Brick Road", Genre.POP, "an important chord or progression that establishes the tonality and groove for the following music", "#Pop #기묘한",
                    "/music/file/musicFile2.mp3",
                    "/music/thumbnail/musicThumbnail2.jpg");
            Music music3 = createMusic(member1, "Right Here Waiting", Genre.ROCK, "If a movement in sonata form starts with an introductory section, this introduction is not usually analyzed", "#OST #저녁",
                    "/music/file/musicFile3.mp3",
                    "/music/thumbnail/musicThumbnail3.png");
            Music music4 = createMusic(member2, "Fix You", Genre.DANCE, "an introduction that works for many songs is the last four or eight measures of the song,", "#초여름 #밤",
                    "/music/file/musicFile4.mp3",
                    "/music/thumbnail/musicThumbnail4.jpg");
            Music music5 = createMusic(member2, "The Water Is Wide", Genre.DISCO, "ntroductions may consist of an ostinato that is used in the following music", "#사랑 #힐링",
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
            Note note1 = Note.createArtistToComposerNote(music1, member3, "노래가 제 취향이에요! 제가 생각한 안무랑 잘 어울리는데 어떠세요??");
            Note note2 = Note.createArtistToComposerNote(music1, member4, "조금만 편곡하면 더 좋은 곡이 될 거 같아요! 같이 작업해보실래요?");
            Note note3 = Note.createArtistToComposerNote(music2, member5, "이 곡으로 가사를 썼는데, 이야기해보고싶어요!");
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
    }

}

package MOAI.moai.note;

import MOAI.moai.common.BaseEntity;
import MOAI.moai.member.Member;
import MOAI.moai.music.Music;
import MOAI.moai.note.type.NoteType;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Note extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msc_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Note note;

    @Enumerated(EnumType.STRING)
    private NoteType dtype;

    @Column(name = "note_cont")
    private String content;

    @Column(name = "req_succ")
    @ColumnDefault("false")
    private Boolean isSuccess;

    // 2차 창작자 -> 1차 창작자 쪽지
    private void artistToComposerInit(Music music, Member member, String content) {
        this.music = music;
        this.member = member;
        this.content = content;
        this.dtype = NoteType.COMPOSER;
        this.note = null;
        this.isSuccess = false;
    }

    // 1차 창작자 -> 2차 창작자 쪽지 (수락)
    private void acceptSuggestionInit(Music music, Member member, Note note) {
        this.music = music;
        this.member = member;
        this.content = member.getNickName() + "님이 제안을 수락하셨습니다.";
        this.dtype = NoteType.NORMAL;
        this.note = note;
        note.isSuccess = true;
    }

    // 1차 창작자 -> 2차 창작자 쪽지 (거절)
    private void declineSuggestionInit(Music music, Member member, Note note) {
        this.music = music;
        this.member = member;
        this.content = member.getNickName() + "님이 제안을 거절하셨습니다.";
        this.dtype = NoteType.NORMAL;
        this.note = note;
    }

    // 2차 창작자 -> 1차 창작자 초기 제안 쪽지
    public static Note createArtistToComposerNote(Music music, Member member, String content) {
        Note note = new Note();
        note.artistToComposerInit(music, member, content);
        return note;
    }

    // 1차 창작자 -> 2차 창작자 수락 쪽지
    public static Note createComposerToArtistAcceptNote(Music music, Member member, Note note) {
        Note newNote = new Note();
        newNote.acceptSuggestionInit(music, member, note);
        return newNote;
    }

    // 1차 창작자 -> 2차 창작자 거절 쪽지
    public static Note createComposerToArtistDeclineNote(Music music, Member member, Note note) {
        Note newNote = new Note();
        newNote.declineSuggestionInit(music, member, note);
        return newNote;
    }


    private void init(Music music, Member member, Note note, NoteType dtype, String content) {
        this.music = music;
        this.member = member;
        this.note = note;
        this.dtype = dtype;
        this.content = content;
    }

    public static Note createNote(Music music, Member member, Note note, NoteType dtype, String content) {
        Note newNote = new Note();
        newNote.init(music, member, note, dtype, content);
        return newNote;
    }

}

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

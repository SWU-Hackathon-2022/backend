package MOAI.moai.note.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *  쪽지 목록 Response 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteListRes {

    private String content;
    private LocalDateTime time;

}

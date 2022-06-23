package MOAI.moai.note.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteListRes {

    private String artistName;
    private String artistProfileImgUrl;
    private String content;
    private LocalDateTime time;

}

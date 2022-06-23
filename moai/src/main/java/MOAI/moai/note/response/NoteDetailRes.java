package MOAI.moai.note.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDetailRes {

    private Long MusicId;
    private Long NoteId;
    private String content;
    private LocalDateTime time;
    private boolean isSuggest;

}

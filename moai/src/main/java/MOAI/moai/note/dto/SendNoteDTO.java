package MOAI.moai.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  쪽지 전송 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendNoteDTO {
    private Long musicId;
    private String content;
}

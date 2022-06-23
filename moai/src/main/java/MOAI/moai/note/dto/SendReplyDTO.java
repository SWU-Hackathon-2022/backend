package MOAI.moai.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  답장 쪽지 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendReplyDTO {

    private Long noteId;
}

package MOAI.moai.music.dto;

import MOAI.moai.member.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicDTO {

    private String musicName;
    private String introduction;
    private String hashTag;
    private Genre genre;
    private MultipartFile musicThumbnail;
    private MultipartFile musicFile;
}

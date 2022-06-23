package MOAI.moai.music.response;

import MOAI.moai.member.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPageMusicRes {
    private String musicThumbnailUrl;
    private String musicFileUrl;
    private String composerNickname;
    private String musicIntro;
    private Genre genre;
    private String hashTag;
}

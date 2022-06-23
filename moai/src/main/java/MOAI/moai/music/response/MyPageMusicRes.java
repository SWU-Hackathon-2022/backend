package MOAI.moai.music.response;

import MOAI.moai.member.genre.Genre;
import MOAI.moai.member.response.MyPageRes;
import MOAI.moai.music.Music;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *  마이페이지 Music response 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageMusicRes {

    private String musicThumbnailURL;
    private String composerThumbnailUrl;
    private String musicName;
    private Genre genre;

    public static List<MyPageMusicRes> createMyPageMusicResList (List<Music> musicList) {
        List<MyPageMusicRes> result = new ArrayList<>();
        for (Music music : musicList) {
            result.add(new MyPageMusicRes(music.getThumbnailUrl(), music.getComposer().getMemberThumbnailUrl(), music.getName(), music.getGenre()));
        }
        return result;
    }
}

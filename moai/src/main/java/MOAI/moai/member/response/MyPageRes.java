package MOAI.moai.member.response;

import MOAI.moai.member.genre.Genre;
import MOAI.moai.music.response.MyPageMusicRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageRes {
    private Long memberId;
    private String nickName;
    private String profileImgUrl;
    private List<Genre> genreList;
    private List<MyPageMusicRes> musicResList;
}

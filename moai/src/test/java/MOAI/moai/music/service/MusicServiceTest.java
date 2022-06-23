package MOAI.moai.music.service;

import MOAI.moai.member.genre.Genre;
import MOAI.moai.music.dto.CreateMusicDTO;
import MOAI.moai.music.response.MainPageMusicRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MusicServiceTest {

    @Autowired MusicService musicService;



}
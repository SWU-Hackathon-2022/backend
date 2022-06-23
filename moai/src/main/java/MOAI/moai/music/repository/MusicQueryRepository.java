package MOAI.moai.music.repository;

import MOAI.moai.music.Music;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import static MOAI.moai.music.QMusic.music;

/**
 *  Music에 대한 동적 쿼리 (QueryDSL)
 */
@Repository
@RequiredArgsConstructor
public class MusicQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Music findMusicBySequence(Long seq) {
        return queryFactory
                .selectFrom(music)
                .offset(seq)
                .limit(1)
                .fetchOne();
    }
}

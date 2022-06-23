package MOAI.moai.note.type;

/**
 *  쪽지 타입
 *  1. COMPOSER : 2차 창작자 -> 1차 창작자로 보내는 제의 쪽지
 *  2. ARTIST   : 1차 창작자 -> 2차 창작자로 보내는 제의 쪽지
 *  3. NORMAL   : 1차 창작자 -> 2차 창작자로 보내는 수락/거절 쪽지
 */
public enum NoteType {
    COMPOSER,
    ARTIST,
    NORMAL
}

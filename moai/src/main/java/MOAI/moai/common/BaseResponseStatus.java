package MOAI.moai.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, HttpStatus.OK, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    INVALID_USER(false,2003, HttpStatus.FORBIDDEN, "권한이 없는 유저의 접근입니다."),

    // users
    USERS_WRONG_LOGIN_ID(false, 2010, HttpStatus.BAD_REQUEST,  "유저 아이디 값을 확인해주세요."),
    USERS_WRONG_PASSWORD(false, 2011, HttpStatus.BAD_REQUEST,  "유저 비밀번호 값을 확인해주세요."),



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, HttpStatus.INTERNAL_SERVER_ERROR, "값을 불러오는데 실패하였습니다."),

    DUPLICATED_EMAIL(false, 3013, HttpStatus.BAD_REQUEST,  "중복된 이메일입니다."),
    DUPLICATED_LOGIN_ID(false, 3014, HttpStatus.BAD_REQUEST,"중복된 로그인 ID입니다."),




    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, HttpStatus.INTERNAL_SERVER_ERROR,"데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, HttpStatus.INTERNAL_SERVER_ERROR, "서버와의 연결에 실패하였습니다."),


    PASSWORD_ENCRYPTION_ERROR(false, 4011, HttpStatus.INTERNAL_SERVER_ERROR,"비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, HttpStatus.INTERNAL_SERVER_ERROR,"비밀번호 복호화에 실패하였습니다."),



    // 5000 : File 오류
    FILE_SAVE_ERROR(false, 5000, HttpStatus.INTERNAL_SERVER_ERROR,"파일 저장에 실패했습니다."),
    FILE_DOWNLOAD_ERROR(false, 5001, HttpStatus.INTERNAL_SERVER_ERROR,"파일 다운로드에 실패했습니다."),


    // 6000 : 외부 API 통신 오류
    EXTERNAL_API_ERROR(false, 5011, HttpStatus.INTERNAL_SERVER_ERROR,"외부 API 통신에 실패했습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    private BaseResponseStatus(boolean isSuccess, int code, HttpStatus httpStatus,  String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}


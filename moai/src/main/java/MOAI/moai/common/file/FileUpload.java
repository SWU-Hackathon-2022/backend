package MOAI.moai.common.file;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 *  FileUpload 담당 클래스
 */
public class FileUpload {

    // member 프로필 사진
    public static String memberThumbnailUpload(MultipartFile file) {
        return "/member/thumbnail/" + fileUpload(File.separator + "resources" + File.separator + "memberThumbnail", file);
    }

    // music 음악 파일
    public static String musicFileUpload(MultipartFile file) {
        return "/music/file/" + fileUpload(File.separator + "resources" + File.separator + "musicFile", file);
    }

    // music 음악 썸네일
    public static String musicThumbnailUpload(MultipartFile file) {
        return "/music/thumbnail/" + fileUpload(File.separator + "resources" + File.separator + "musicThumbnail", file);
    }

    // 2차 창작물 영상
    public static String creationFileUpload(MultipartFile file) {
        return "/creation/file/" + fileUpload(File.separator + "resources" + File.separator + "creationFile", file);
    }


    /**
     *
     *  공통 파일 업로드 메서드
     */
    public static String fileUpload (String path, MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File target = new File(path, fileName);

        // 경로 생성
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }

        // 파일 복사
        try {
            FileCopyUtils.copy(file.getBytes(), target);
            return fileName;
        }
        catch (Exception e) {
            return null;
        }
    }
}

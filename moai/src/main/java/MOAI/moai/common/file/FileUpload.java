package MOAI.moai.common.file;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUpload {

    // member 프로필 사진
    public static String memberThumbnailUpload(MultipartFile file) {
        return fileUpload("/memberThumbnail", file);
    }

    // music 음악 파일
    public static String musicFileUpload(MultipartFile file) {
        return fileUpload("/musicFile", file);
    }

    // music 음악 썸네일
    public static String musicThumbnailUpload(MultipartFile file) {
        return fileUpload("/musicThumbnail", file);
    }

    // 2차 창작물 영상
    public static String creationFileUpload(MultipartFile file) {
        return fileUpload("/creationFile", file);
    }


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
            return path + "/" + fileName;
        }
        catch (Exception e) {
            return null;
        }
    }
}

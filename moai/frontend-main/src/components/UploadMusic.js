import { useState } from "react";
import axios from "axios";
import classes from "../../src/assets/css/UploadMusic.module.css";
import ImageUploading from "react-images-uploading";

const baseURL = "http://localhost:8080";

const UploadMusic = () => {
  const [images, setImages] = useState([]);
  const maxNumber = 1;

  const onChange = (imageList, addUpdateIndex) => {
    // data for submit
    console.log(imageList, addUpdateIndex);
    setImages(imageList);
  };

  // 추가
  const onError = (errors, files) => {
    if (errors.maxNumber) {
      alert("이미지는 1개까지만 첨부할 수 있습니다");
    }
  };

  const [uploadMusicData, setUploadMusicData] = useState({
    musicName: "",
    introduction: "",
    hashTag: "",
    genre: "",
    musicThumbnail: "",
    musicFile: "",
  });

  const handleSelect = (e) => {
    setUploadMusicData({ ...uploadMusicData, genre: e.target.value });
  };

  const onImageFileChange = (img) => {
    console.log(img);
    localStorage.setItem("img", JSON.stringify(img));

    console.log(localStorage.getItem("img"));

    setUploadMusicData({
      ...uploadMusicData,
      musicThumbnail: img,
    });
  };

  const onMusicFileChange = (event) => {
    setUploadMusicData({
      ...uploadMusicData,
      musicFile: event.target.files[0],
    });
  };

  const submitMusicDataHandler = async (e) => {
    e.preventDefault();

    const musicThumbnail = uploadMusicData.musicThumbnail;
    const musicFile = uploadMusicData.musicFile;
    const musicThumbnailName = musicThumbnail.name;
    const musicFileName = musicFile.name;

    const formData = new FormData();
    formData.append("musicName", uploadMusicData.musicName);
    formData.append("introduction", uploadMusicData.introduction);
    formData.append("hashTag", uploadMusicData.hashTag);
    formData.append("genre", uploadMusicData.genre);
    formData.append("Thumbnail", musicThumbnail);
    formData.append("musicFile", musicFile);

    const response = await axios.post(`${baseURL}/music/new`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": "*",
      },
    });

    console.log(response);
  };

  const fileSelectedHandler = (event) => {
    setUploadMusicData({
      ...uploadMusicData,
      musicThumbnail: event.target.files[0],
    });
  };

  console.log(uploadMusicData);

  return (
    <div className={classes.UploadMusic}>
      <div className={classes.UploadMusicNavigation}>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="40"
          fill="currentColor"
          className="bi bi-chevron-left"
          viewBox="0 0 16 16"
        >
          <path
            fillRule="evenodd"
            d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"
          />
        </svg>
        <h5>logo</h5>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="26"
          height="26"
          fill="currentColor"
          className="bi bi-bell-fill"
          viewBox="0 0 16 16"
        >
          <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z" />
        </svg>
      </div>
      <form onSubmit={submitMusicDataHandler}>
        <div>
          <label htmlFor="musicName">음악이름</label>
          <input
            onChange={(e) =>
              setUploadMusicData({
                ...uploadMusicData,
                musicName: e.target.value,
              })
            }
            type="text"
            name="musicName"
            id="musicName"
          ></input>
        </div>
        <div>
          <label htmlFor="introduce">음악소개</label>
          <textarea
            onChange={(e) =>
              setUploadMusicData({
                ...uploadMusicData,
                introduction: e.target.value,
              })
            }
            type="text"
            name="introduce"
            id="introduce"
            style={{
              height: "100px",
              resize: "none",
            }}
          ></textarea>
        </div>
        <div>
          <label htmlFor="hashTag">해시태그</label>
          <input
            onChange={(e) =>
              setUploadMusicData({
                ...uploadMusicData,
                hashTag: e.target.value,
              })
            }
            type="text"
            name="hashTag"
            id="hadhTag"
          ></input>
        </div>

        <div>
          <label>장르</label>
          <select defaultValue={""} onChange={handleSelect}>
            <option value="HIP_HOP">힙합</option>
            <option value="POP">POP</option>
            <option value="ROCK">락</option>
            <option value="JAZZ">재즈</option>
            <option value="BALLAD">발라드</option>
            <option value="DANCE">댄스</option>
            <option value="DISCO">디스코</option>
            <option value="R_B">R&B</option>
            <option value="INDIE">인디</option>
          </select>
        </div>

        <div className={classes.UploadMusicThumbnail}>
          <label>이미지 업로드</label>
          <input type="file" onChange={fileSelectedHandler}></input>
        </div>

        <div>
          <label htmlFor="musicFile">음악 파일 업로드</label>
          <input type="file" onInput={onMusicFileChange} id="musicFile" />
        </div>
        <button type="submit">음악 업로드</button>
      </form>
    </div>
  );
};

export default UploadMusic;

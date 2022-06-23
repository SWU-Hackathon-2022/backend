import React, { Fragment, useState } from "react";
import classes from "../assets/css/Main.module.css";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import ReactAudioPlayer from "react-audio-player";

const Main = (props) => {
  console.log(props.login);
  const baseURL = "http://localhost:8080";
  const [mainPageData, setMainPageData] = useState({
    musicThumbnailUrl: "",
    musicFileUrl: "",
    composerNickname: "",
    composerThumbnailUrl: "",
    musicIntro: "",
    genre: "",
  });

  useEffect(() => {
    const getMusicListDataFromServer = async () => {
      const response = await axios.get(`${baseURL}/music/1`, {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        token: "token1",
      });
      const responseData = await response.data;
      const responseDataResult = await responseData.result;

      const responseDataHashTag = responseDataResult.hashTag.split("#");
      const responseDataHashTagFilter = responseDataHashTag.filter((el) => el.length);

      responseDataResult.hashTag = responseDataHashTagFilter;
      setMainPageData(responseDataResult);
    };
    getMusicListDataFromServer();
  }, []);

  console.log(mainPageData);

  return (
    <>
      <Fragment className={classes.Background}>
        <Header></Header>
        <div className={classes.Main}>
          <div className={classes.MainImgAndTag}>
            <img
              src={`${mainPageData.musicThumbnailUrl}`}
              alt="앨범 이미지"
              style={{
                width: "375px",
                height: "375px",
                zIndex: "-1",
              }}
            />

            <ul className={classes.MainImgAndTag__flex}>
              {mainPageData.hashTag?.map((el, idx) => (
                <li className={classes.MainImgAndTag__tag} key={idx}>{`#${el}`}</li>
              ))}
            </ul>
            <div className={classes.MainImgAndTag__ltgt}>
              <svg xmlns="http://www.w3.org/2000/svg" width="17" height="30" fill="currentColor" className="bi bi-chevron-left" viewBox="0 0 16 16">
                <path fillRule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
              </svg>
              <svg xmlns="http://www.w3.org/2000/svg" width="17" height="30" fill="currentColor" className="bi bi-chevron-right" viewBox="0 0 16 16">
                <path fillRule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
              </svg>
            </div>
            <div className={classes.ProfileImgAndTitle}>
              <img src={`${mainPageData.composerThumbnailUrl}`} alt="메인페이지 사람 이미지" />
              <p>{`${mainPageData.composerNickname}`}</p>
            </div>
          </div>
          <div className="d-flex justify-content-center">
            <ReactAudioPlayer src="https://www.bensound.com/bensound-music/bensound-memories.mp3" autoPlay controls />
          </div>
          <div className={classes.MusicTitleAndDescription}>
            <div className={classes.MusicTitleAndDescription__genre}>
              <h5>{mainPageData.genre}</h5>
            </div>
            <div>
              <h3 className={classes.MusicTitleAndDescription__title}>{mainPageData.musicIntro}</h3>
              <p className={classes.MusicTitleAndDescription__description}>
                언덕에 올라가는 느낌을 비트로 나타내어 보았습니다. 소중한 곡이라서 가지고 있었는데, 더 이상 썩혀두기 아까워서 올려봅니다.곡의 리듬감을 잘 살려줄 목소리를 찾아요.
              </p>
            </div>
          </div>
          <div className="d-flex justify-content-center">
            <Link to="/message_send" className="">
              <button className={classes.Main__button}>
                <svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" className="bi bi-chat-left-dots-fill" viewBox="0 0 16 16" style={{ marginRight: "10px" }}>
                  <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H4.414a1 1 0 0 0-.707.293L.854 15.146A.5.5 0 0 1 0 14.793V2zm5 4a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                </svg>
                음원 사용 제안하기
              </button>
            </Link>
          </div>
        </div>
      </Fragment>
    </>
  );
};

export default Main;

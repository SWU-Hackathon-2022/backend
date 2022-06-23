import classes from "../assets/css/MyPage.module.css";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";

const baseURL = "http://localhost:8080";

const MyPage = () => {
  const [myPageData, getMyPageData] = useState({
    genreList: [],
    memberId: null,
    musicResList: [],
    nickName: "",
    profileImgUrl: "",
  });

  useEffect(() => {
    const getUserDataFromServer = async () => {
      const response = await axios.get(`${baseURL}/mypage`, {
        "Content-Type": "application/json",
        "Cross-Control-Allow-Origin": "*",
        token: "token1",
      });
      const responseData = await response.data.result;
      getMyPageData(responseData);
    };

    try {
      getUserDataFromServer();
    } catch (err) {
      console.error(err);
    }
  }, []);

  return (
    <>
      <div className={classes.MyPage}>
        <div className={classes.MainHeader}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
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
          <h5 className={classes.MainHeader__logo}>logo</h5>
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

        <div className={classes.Profile}>
          <div className={classes.Profile__ImgNickname}>
            <img
              src={require("../assets/images/header__img.png")}
              alt="임시이미지"
            />
            <p className={classes.Profile__Nickname}>{myPageData?.nickName}</p>
            <p className={classes.Profile__NicknameAt}>@user_id</p>
          </div>
        </div>

        <div className={classes.Add}>
          <Link to="/upload_music" className={classes.Add__button}>
            곡 추가
          </Link>
        </div>

        <div className={classes.MainGenre}>
          <div className={classes.MainGenre__group}>
            <div
              style={{
                margin: 0,
                padding: 0,
                display: "flex",
                flexDirection: "row",
                gap: "4px",
              }}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="12"
                height="14"
                fill="currentColor"
                className="bi bi-file-music-fill"
                viewBox="0 0 16 16"
                style={{ color: "#ffffff", marginTop: "3px" }}
              >
                <path d="M12 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm-.5 4.11v1.8l-2.5.5v5.09c0 .495-.301.883-.662 1.123C7.974 12.866 7.499 13 7 13c-.5 0-.974-.134-1.338-.377-.36-.24-.662-.628-.662-1.123s.301-.883.662-1.123C6.026 10.134 6.501 10 7 10c.356 0 .7.068 1 .196V4.41a1 1 0 0 1 .804-.98l1.5-.3a1 1 0 0 1 1.196.98z" />
              </svg>
              <p>주 장르</p>
            </div>
            <p>{myPageData?.genreList.join(", ")}</p>
          </div>
        </div>
        <div className={classes.MainGenre__triangle}></div>
        <div className={classes.MyPage__grid}>
          {myPageData?.musicResList.map((el, idx) => {
            return (
              <div className={classes.MyPage__gridItem} key={idx}>
                <div className={classes.MyPage__gridItemTag}>
                  <p>{el.genre}</p>
                </div>
                <img
                  src={require("../assets/images/header__img.png")}
                  alt="샘플 이미지"
                />
                <div className={classes.MyPage__gridItemTitle}>
                  <h5>{el.musicName}</h5>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
};

export default MyPage;

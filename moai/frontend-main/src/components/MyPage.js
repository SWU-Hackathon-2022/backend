import classes from "../assets/css/MyPage.module.css";
import axios from "axios";
import { Fragment, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { Image, Navbar, Container, Nav } from "react-bootstrap";
import styles from "../assets/css/MessageCard.module.css";
import classNames from "classnames/bind";
import Logo from "../assets/images/logo.png";

const cx = classNames.bind(styles);

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
    <Fragment className={styles.page}>
      <Navbar bg="none" expand="lg" className="d-flex justify-content-between mb-3">
        <Container className="d-flex align-items-center">
          <Nav.Link href="javascript:window.history.back();">
            <svg xmlns="http://www.w3.org/2000/svg" width="34.5" height="34.5" fill="black" class="bi bi-chevron-left" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
            </svg>
          </Nav.Link>
          <Navbar.Brand href="/" className={cx("message__logo")}>
            <Image src={Logo} width="60px"></Image>
          </Navbar.Brand>
          <Nav.Link href="/upload_music">
            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="black" class="bi bi-pencil-square" viewBox="0 0 16 16">
              <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
              <path
                fill-rule="evenodd"
                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
              />
            </svg>
          </Nav.Link>
        </Container>
      </Navbar>
      <div className={classes.MyPage}>
        <div className={classes.Profile}>
          <div className={classes.Profile__ImgNickname}>
            <img src={require("../assets/images/header__img.png")} alt="임시이미지" />
            <p className={classes.Profile__Nickname}>{myPageData?.nickName}</p>
            <p className={classes.Profile__NicknameAt}>@clados123@gmail.com</p>
          </div>
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
              <svg xmlns="http://www.w3.org/2000/svg" width="12" height="14" fill="currentColor" className="bi bi-file-music-fill" viewBox="0 0 16 16" style={{ color: "#ffffff", marginTop: "3px" }}>
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
                <img src={require("../assets/images/header__img.png")} alt="샘플 이미지" />
                <div className={classes.MyPage__gridItemTitle}>
                  <h5>{el.musicName}</h5>
                </div>
              </div>
            );
          })}
        </div>
        <div className={classes.Add}>
          <Link to="/upload_music" className={classes.Add__button}>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z" />
            </svg>
          </Link>
        </div>
      </div>
    </Fragment>
  );
};

export default MyPage;

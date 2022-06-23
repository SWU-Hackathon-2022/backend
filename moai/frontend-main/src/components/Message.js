import React, { useEffect, useState } from "react";
import { Fragment } from "react";
import { Row, Col, Navbar, Nav, Container } from "react-bootstrap";
import MessageCard from "../elements/MessageCard";

import styles from "../assets/css/MessageCard.module.css";
import classNames from "classnames/bind";
import Header from "./Header";
import axios from "axios";

const cx = classNames.bind(styles);

const Message = (props) => {
  //   const [messageList, setMessageList] = useState();

  //   const user = props.id; //사용자 id
  //   const useEffect = () => {
  //     getMessageList(user);
  //   };

  // const getMessageList = async (user) => {
  //   const response = await axios.get("" + user);
  //   setMessageList(response.data);
  // };

  const baseURL = "http://localhost:8080";

  const [messageList, setMessageList] = useState({
    artistName: "",
    artistProfileImgUrl: "",
    content: "",
    time: "",
  });

  useEffect(() => {
    const getUserDataFromServer = async () => {
      const response = await axios.get(`${baseURL}/note`, {
        "Content-Type": "application/json",
        "Cross-Control-Allow-Origin": "*",
      });
      const responseData = await response.data.result;
      setMessageList(responseData);
    };

    try {
      getUserDataFromServer();
    } catch (err) {
      console.error(err);
    }
  }, []);

  //   const messageList = [
  //     {
  //       id: 0,
  //       image: "https://2e4efd3ddd5ec0b50028-7d521b783d142fa14612a0034dea730a.ssl.cf2.rackcdn.com/gallery/2008/08/3190854_1316217600_gallery_image_3072799.jpg",
  //       name: "김나비",
  //       message: "안녕하세요 같이 협업 할 수 있을까요 알아맞춰보세요 딩동댕동 커피잔",
  //       time: "22.06.30",
  //     },
  //     {
  //       id: 1,
  //       image: "https://farm8.staticflickr.com/7007/6392178127_177ea51b56_b.jpg",
  //       name: "비욘세",
  //       message: "노래가 너무 좋아요!",
  //       time: "22.06.22",
  //     },
  //   ];

  const messagelist = messageList.map((event) => (
    <MessageCard
      id={event.id}
      image={event.image}
      name={event.name}
      message={event.message}
      time={event.time}
    />
  ));

  return (
    <div className={cx("background-grey")}>
      <Header />
      <Fragment>
        <Row className="m-1 align-items-start justify-content-center min-vh-100">
          <Col lg={5} md={5} className="py-8 py-xl-0">
            {messagelist}
          </Col>
        </Row>
      </Fragment>
    </div>
  );
};

export default Message;

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

  // const [messageList, setMessageList] = useState({
  //   artistName: "",
  //   artistProfileImgUrl: "",
  //   content: "",
  //   time: "",
  // });

  // useEffect(() => {
  //   const getUserDataFromServer = async () => {
  //     const response = await axios.get(`${baseURL}/note`, {
  //       "Content-Type": "application/json",
  //       "Cross-Control-Allow-Origin": "*",
  //     });
  //     const responseData = await response.data.result;
  //     setMessageList(responseData);
  //   };

  //   try {
  //     getUserDataFromServer();
  //   } catch (err) {
  //     console.error(err);
  //   }
  // }, []);

  const messageList = [
    {
      id: 0,
      image: "https://2e4efd3ddd5ec0b50028-7d521b783d142fa14612a0034dea730a.ssl.cf2.rackcdn.com/gallery/2008/08/3190854_1316217600_gallery_image_3072799.jpg",
      name: "김철수",
      message: "안녕하세요! 저는 음악을 전공하고 있는 김철수입니다.",
      time: "22.06.24",
    },
    {
      id: 1,
      image: "https://farm8.staticflickr.com/7007/6392178127_177ea51b56_b.jpg",
      name: "장나라",
      message: "노래가 너무 좋아요!",
      time: "22.06.22",
    },
    {
      id: 3,
      image: "https://www.w3schools.com/html/pic_trulli.jpg",
      name: "현빈",
      message: "인디 음악을 사랑하는 사람으로서 이런 음악이 있다는게 너무 좋네요.",
      time: "22.06.20",
    },
  ];

  const messagelist = messageList.map((event) => <MessageCard id={event.id} image={event.image} name={event.name} message={event.message} time={event.time} />);

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

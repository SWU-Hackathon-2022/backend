import React, { useEffect, useState } from "react";
import { Card, Row, Col, Button, Form } from "react-bootstrap";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";

import styles from "../assets/css/MessageCard.module.css";
import classNames from "classnames/bind";

import Header from "./Header";

const cx = classNames.bind(styles);

const MessageReceived = () => {
  const id = useParams();
  const myid = "3";

  // const [messageInfo, setMessageInfo] = useState();

  // useEffect(() => {
  //   readMessage(id);
  // }, []);

  // const readMessage = async (id) => {
  //   const response = await axios.get("" + id);
  //   setMessageInfo(response.data);
  // };

  const [textarea, setTextarea] = useState("");
  const [status, setStatus] = useState(0);

  const navigate = useNavigate();

  const handleChange = (event) => {
    setTextarea(event.target.value);
  };

  const sendMessage = async (e) => {
    console.log("click login");
    e.preventDefault();
    var params = new URLSearchParams();

    params.append("message", textarea);

    // const response = await axios.post("", params);
    navigate("/message");
  };

  const clickAccept = () => {
    setStatus(1);
  };

  const clickDecline = () => {
    setStatus(-1);
  };

  const messageInfo = [
    {
      id: 0,
      image: "https://2e4efd3ddd5ec0b50028-7d521b783d142fa14612a0034dea730a.ssl.cf2.rackcdn.com/gallery/2008/08/3190854_1316217600_gallery_image_3072799.jpg",
      name: "김나비",
      message: "안녕하세요 같이 협업 할 수 있을까요 제 이름은 김나부랭이이고 이 음악을 너무 감명 깊게 들어서 눈물을 흘렸습니다. 부디 이 음악을 사용할 수 있게 해 주십쇼.",
      time: "AM 10:00",
      memberid: "12",
    },
    {
      id: 0,
      image: "https://2e4efd3ddd5ec0b50028-7d521b783d142fa14612a0034dea730a.ssl.cf2.rackcdn.com/gallery/2008/08/3190854_1316217600_gallery_image_3072799.jpg",
      name: "김나비",
      message: " 만나서 반갑습니다. 대단히 고맙습니다. ",
      time: "AM 10:00",
      memberid: "3",
    },
  ];

  return (
    <div className={cx("background-grey")}>
      <Header />
      <Row className="m-1 align-items-start justify-content-center min-vh-100">
        <Col lg={5} md={5} className="py-8 py-xl-0">
          {messageInfo.map((message) => {
            console.log(message.name);
            if (message.memberid === myid) {
              return (
                <div className={cx("my-message-bubble")}>
                  <div className={cx("message__time")}>{message.time}</div>
                  <Card className={cx("my-message")}>
                    <div className={cx("my-message-bubble__text")}>{message.message}</div>
                  </Card>
                </div>
              );
            } else {
              return (
                <div className={cx("message-bubble")}>
                  <Card className={cx("message")}>
                    <div className={cx("message__name")}>{message.name}</div>
                    <div className={cx("message-bubble__text")}>{message.message}</div>
                  </Card>
                  <div className={cx("message__time")}>{message.time}</div>
                </div>
              );
            }
          })}
        </Col>
        {status === 0 ? (
          <div className="d-flex flex-row align-items-center justify-content-center">
            <div className={cx("text-muted")}>연락을 주고받기 위해서 수락 해 주세요.</div>
            <div className={cx("message__button--bottom")}>
              <Button className={cx("bottom-button", "grey")} onClick={clickDecline}>
                거절
              </Button>
              <Button className={cx("bottom-button", "blue")} onClick={clickAccept}>
                수락
              </Button>
            </div>
          </div>
        ) : (
          <>
            {status === 1 ? (
              <Form onSubmit={sendMessage} className={cx("message-send--bottom")}>
                <div className="d-flex align-items-center">
                  <div className={styles.sendTextarea}>
                    <Form.Control className={styles.sendTextarea__text} as="textarea" rows={1} placeholder="쪽지 내용을 입력하세요." value={textarea} onChange={handleChange} />
                  </div>
                  <div>
                    <Button type="submit" className={styles.sendButton}>
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send-fill" viewBox="0 0 16 16">
                        <path d="M15.964.686a.5.5 0 0 0-.65-.65L.767 5.855H.766l-.452.18a.5.5 0 0 0-.082.887l.41.26.001.002 4.995 3.178 3.178 4.995.002.002.26.41a.5.5 0 0 0 .886-.083l6-15Zm-1.833 1.89L6.637 10.07l-.215-.338a.5.5 0 0 0-.154-.154l-.338-.215 7.494-7.494 1.178-.471-.47 1.178Z" />
                      </svg>
                    </Button>
                  </div>
                </div>
              </Form>
            ) : (
              <>
                <div className={cx("end-message--bottom")}>
                  <div className="d-flex align-items-center">더 이상 대화를 진행 하실 수 없습니다.</div>
                </div>
              </>
            )}
          </>
        )}
      </Row>
    </div>
  );
};

export default MessageReceived;

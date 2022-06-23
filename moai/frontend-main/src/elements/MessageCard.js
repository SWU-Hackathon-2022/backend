import React from "react";
import { Link } from "react-router-dom";
import { Card, Image } from "react-bootstrap";
import MessageReceived from "../components/MessageReceived";

import styles from "../assets/css/MessageCard.module.css";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);

const MessageCard = (props) => {
  const link = "/message_received/" + props.id.toString();

  return (
    <>
      <Link to={link} className="text-decoration-none text-dark">
        <div className={cx("message-card")}>
          <div>
            <div>
              <Image src={props.image} alt="" className={cx("message__image")}></Image>
            </div>
          </div>
          <div>
            <div className="row">
              <div className="col d-flex flex-div justify-content-between">
                <div className={cx("message__name")}>{props.name}</div>
                <div className={cx("message__time")}>{props.time}</div>
              </div>
            </div>
            <div className="row">
              <div className={cx("message__text")}>{props.message}</div>
            </div>
          </div>
        </div>
      </Link>
    </>
  );
};

export default MessageCard;

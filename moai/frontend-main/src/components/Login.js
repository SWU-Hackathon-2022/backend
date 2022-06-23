import { Fragment, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Col, Row, Card, Form, Button, Image } from "react-bootstrap";

import Logo from "../assets/images/logo.png";
import styles from "../assets/css/Login.module.css";

import classNames from "classnames/bind";
import axios from "axios";

const cx = classNames.bind(styles);

const Login = () => {
  const baseURL = "http://localhost:8080";
  const navigate = useNavigate();

  const [nickname, setNickname] = useState("");
  const [password, setPassword] = useState("");

  const handleNickname = (e) => {
    setNickname(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const loginUser = async (e) => {
    console.log("click login");
    e.preventDefault();
    var params = new URLSearchParams();

    params.append("nickname", nickname);
    params.append("password", password);

    // const response = await axios.post("", params);
    navigate("/");
  };

  const triggerLoginHandler = async (e) => {
    e.preventDefault();
    const response = await axios.post(
      `${baseURL}/login`,
      {
        loginId: nickname,
        password: password,
      },
      {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        withCredentials: true,
        token: "token1",
      }
    );

    console.log(response);

    const responseData = await response.data.result;
    navigate("/mypage");
  };

  return (
    <Fragment>
      <Row className="m-1 align-items-center justify-content-center min-vh-100">
        <Col lg={5} md={5} className="py-8 py-xl-0">
          <Card>
            <Card.Body className="p-6">
              <div className="mb-4">
                <Link to="/">
                  <Image src={Logo} alt="" className={cx("logo__image")} />
                </Link>
                <h1 className="mt-4 mb-1 fw-bold">로그인</h1>
                <span>
                  아직 계정이 없으신가요?{" "}
                  <Link to="/register" className="ms-1">
                    회원가입
                  </Link>
                </span>
              </div>
              {/* Form */}
              <Form onSubmit={triggerLoginHandler}>
                <Row>
                  <Col lg={12} md={12} className="mb-3">
                    {/* Username */}
                    <Form.Label>ID </Form.Label>
                    <Form.Control type="text" id="nickname" placeholder="아이디를 입력하세요" value={nickname} onChange={handleNickname} required />
                  </Col>
                  <Col lg={12} md={12} className="mb-3">
                    {/* Password */}
                    <Form.Label>비밀번호 </Form.Label>
                    <Form.Control type="password" id="password" placeholder="**************" value={password} onChange={handlePassword} required />
                  </Col>

                  <Col lg={12} md={12} className="mb-0 d-grid gap-2">
                    {/* Button */}
                    {/* <Link to="/MyPage"> */}
                    <Button variant="primary" type="submit">
                      로그인
                    </Button>
                    {/* </Link> */}
                  </Col>
                </Row>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Fragment>
  );
};

export default Login;

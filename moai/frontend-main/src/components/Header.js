import { Navbar, Nav, Container, Image } from "react-bootstrap";
import styles from "../assets/css/MessageCard.module.css";
import classNames from "classnames/bind";
import Logo from "../assets/images/logo.png";

const cx = classNames.bind(styles);

const Header = () => {
  return (
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

        <Nav.Link href="/mypage">
          <svg xmlns="http://www.w3.org/2000/svg" width="34.5" height="34.5" fill="black" class="bi bi-person-fill" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
          </svg>
        </Nav.Link>
      </Container>
    </Navbar>
  );
};

export default Header;

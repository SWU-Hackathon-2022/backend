import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Main from "./components/Main";
import Login from "./components/Login";
import NotFound from "./components/NotFound";
import Message from "./components/Message";
import MessageReceived from "./components/MessageReceived";
import MessageSend from "./components/MessageSend";
import MyPage from "./components/MyPage";
import UploadMusic from "./components/UploadMusic";
import LoginGoogle from "./components/LoginGoogle";

const App = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <header className="App-header"></header>
        <Routes>
          <Route path="/" element={<Main />}></Route>
          {/* <Route path="/login" element={<Login />}></Route> */}
          <Route path="/mypage" element={<MyPage />}></Route>
          <Route path="*" element={<NotFound />}></Route>
          <Route path="/message" element={<Message />}></Route>
          <Route
            path="/message_received/:id"
            element={<MessageReceived />}
          ></Route>
          <Route path="/message_send" element={<MessageSend />}></Route>
          <Route path="/upload_music" element={<UploadMusic />}></Route>
          <Route path="/login" element={<LoginGoogle />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;

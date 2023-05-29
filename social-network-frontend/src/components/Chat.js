import { BookmarkIcon, PaperAirplaneIcon, UserGroupIcon } from '@heroicons/react/24/outline';
import jwtDecode from 'jwt-decode';
import React, { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import useAuth from '../hooks/useAuth';

var stompClient = null;

const ChatRoom = () => {
  const { auth } = useAuth();
  const messagesEndRef = useRef(null);
  const decoded = jwtDecode(auth?.token);

  const [privateChats, setPrivateChats] = useState(new Map());
  const [publicChats, setPublicChats] = useState([]);
  const [tab, setTab] = useState("CHATROOM");
  const [userData, setUserData] = useState({
    username: decoded.sub,
    receivername: '',
    connected: false,
    message: ''
  });

  useEffect(() => {
    return () => {
      connect();
    };
  }, []);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [publicChats, privateChats]);

  const connect = () => {
    let Sock = new SockJS('http://localhost:8081/ws');
    stompClient = over(Sock);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    setUserData({ ...userData, "connected": true });
    stompClient.subscribe('/chatroom/public', onMessageReceived);
    stompClient.subscribe('/user/' + userData.username + '/private', onPrivateMessage);
    userJoin();
  };

  const userJoin = () => {
    var chatMessage = {
      senderName: userData.username,
      status: "JOIN"
    };
    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };

  const onMessageReceived = (payload) => {
    var payloadData = JSON.parse(payload.body);
    switch (payloadData.status) {
      case "JOIN":
        if (!privateChats.get(payloadData.senderName)) {
          privateChats.set(payloadData.senderName, []);
          setPrivateChats(new Map(privateChats));
        }
        break;
      case "MESSAGE":
        publicChats.push(payloadData);
        setPublicChats([...publicChats]);
        break;
      default:
        console.log("Unknown status");
    }
  };

  const onPrivateMessage = (payload) => {
    console.log(payload);
    var payloadData = JSON.parse(payload.body);
    if (privateChats.get(payloadData.senderName)) {
      privateChats.get(payloadData.senderName).push(payloadData);
      setPrivateChats(new Map(privateChats));
    } else {
      let list = [];
      list.push(payloadData);
      privateChats.set(payloadData.senderName, list);
      setPrivateChats(new Map(privateChats));
    }
  };

  const onError = (err) => {
    console.log(err);

  };

  const handleMessage = (event) => {
    const { value } = event.target;
    setUserData({ ...userData, "message": value });
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      console.log('Enter is pressed, sending message.');
      userData.message ? sendValue() : showEmptyMessageAlert();
    }
  };

  const handleKeyDownPrivate = (event) => {
    if (event.key === 'Enter') {
      console.log('Enter is pressed, sending message.');
      userData.message ? sendPrivateValue() : showEmptyMessageAlert();
    }
  };

  const showEmptyMessageAlert = () => {
    alert("You cannot send an empty message!");
  };

  const sendValue = () => {
    if (stompClient) {
      var chatMessage = {
        senderName: userData.username,
        message: userData.message,
        status: "MESSAGE"
      };
      console.log(chatMessage);
      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, "message": "" });
    }
  };

  const sendPrivateValue = () => {
    if (stompClient) {
      var chatMessage = {
        senderName: userData.username,
        receiverName: tab,
        message: userData.message,
        status: "MESSAGE"
      };

      if (userData.username !== tab) {
        privateChats.get(tab).push(chatMessage);
        setPrivateChats(new Map(privateChats));
      }
      stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, "message": "" });
    }
  };

  return (
    <div className='relative'>
      <span className='text-xl font-bold'>Your chats</span>

      {userData.connected &&
        <div className="chat-box">

          {/*Chats list*/}
          <div className="member-list">
            <ul className='space-y-3'>
              <li onClick={() => { setTab("CHATROOM"); }} className={`member ${tab === "CHATROOM" && "active"}`}>
                <UserGroupIcon className='h-8 w-8' />
                <span>Group chat</span>
              </li>

              {[...privateChats.keys()].map((name, index) => (
                name !== userData.username
                  ?
                  (
                    <li onClick={() => { setTab(name); }} className={`member ${tab === name && "active"}`} key={index}>
                      <img
                        className="h-8 w-8 rounded-full"
                        src="/assets/profile-picture-1.jpg"
                        // src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                        alt="logo"
                      />
                      <span>{name}</span>
                    </li>
                  )
                  :
                  (
                    <li onClick={() => { setTab(name); }} className={`member ${tab === name && "active"}`} key={index}>
                      <BookmarkIcon className='h-8 w-8' />
                      <span>Saved messages</span>
                    </li>
                  )
              ))}
            </ul>
          </div>

          {/* Public chat */}
          {tab === "CHATROOM" && <div className="chat-content">
            <ul className="chat-messages">
              {publicChats.map((chat, index) => (
                <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                  {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                  <div className="message-data">{chat.message}</div>
                  {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                </li>
              ))}
              <div ref={messagesEndRef} />
            </ul>

            <div className="send-message">
              <input type="text" className="input-message" placeholder="Enter your message" value={userData.message} onChange={handleMessage} onKeyDown={handleKeyDown} />
              <PaperAirplaneIcon type="button" className="send-button" onClick={userData.message ? sendValue : showEmptyMessageAlert} />
            </div>

          </div>}

          {/* Private chat */}
          {tab !== "CHATROOM" && <div className="chat-content">
            <ul className="chat-messages">
              {[...privateChats.get(tab)].map((chat, index) => (
                <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                  {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                  <div className="message-data">{chat.message}</div>
                  {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                </li>
              ))}
              <div ref={messagesEndRef} />
            </ul>

            <div className="send-message">
              <input type="text" className="input-message" placeholder="Enter your message" value={userData.message} onChange={handleMessage} onKeyDown={handleKeyDownPrivate} />
              <PaperAirplaneIcon type="button" className="send-button" onClick={userData.message ? sendPrivateValue : showEmptyMessageAlert} />
            </div>

          </div>}

        </div>

      }
    </div>
  );
};

export default ChatRoom;

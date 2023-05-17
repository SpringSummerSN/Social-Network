import { faCheck } from '@fortawesome/free-solid-svg-icons/faCheck';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons/faInfoCircle';
import { faTimes } from '@fortawesome/free-solid-svg-icons/faTimes';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useRef, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import axios from '../api/axios';

const EMAIL_REGEX = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = '/register';

const Register = () => {
  const userRef = useRef();
  const errRef = useRef();

  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || '/';

  const [email, setEmail] = useState('');
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  const [userName, setUserName] = useState('');
  const [validUserName, setValidUserName] = useState(false);
  const [userNameFocus, setUserNameFocus] = useState(false);

  const [userSurname, setUserSurname] = useState('');
  const [validUserSurname, setValidUserSurname] = useState(false);
  const [userSurameFocus, setUserSurnameFocus] = useState(false);

  const [pwd, setPwd] = useState('');
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState('');
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState('');
  const [success, setSuccess] = useState(false);

  // add focus when component loads
  useEffect(() => {
    userRef.current.focus();
  }, []);

  // everytime email, name, surname fields change -> validate
  useEffect(() => {
    const result = EMAIL_REGEX.test(email);
    console.log(result);
    console.log(email);
    setValidEmail(result);
  }, [email]);

  useEffect(() => {
    const result = USER_REGEX.test(userName);
    console.log(result);
    console.log(email);
    setValidUserName(result);
  }, [userName]);

  useEffect(() => {
    const result = USER_REGEX.test(userSurname);
    console.log(result);
    console.log(email);
    setValidUserSurname(result);
  }, [userSurname]);

  // everytime pwd or mathcPwd changes ->
  useEffect(() => {
    const result = PWD_REGEX.test(pwd);
    console.log(result);
    console.log(pwd);
    setValidPwd(result);

    const match = pwd === matchPwd;
    setValidMatch(match);
  }, [pwd, matchPwd]);

  // everytime user, pwd, matchPwd changes -> clear errMsg
  useEffect(() => {
    setErrMsg('');
  }, [email, userName, userSurname, pwd, matchPwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // additional layer if button bypassed with JS code
    const v1 = EMAIL_REGEX.test(email);
    const v2 = USER_REGEX.test(userName);
    const v3 = USER_REGEX.test(userSurname);
    const v4 = PWD_REGEX.test(pwd);
    if (!v1 || !v2 || !v3 || !v4) {
      setErrMsg('Invalid Entry!');
      return;
    }

    // TODO: change naming in POST
    try {
      const response = await axios.post(
        REGISTER_URL,
        JSON.stringify({
          email: email,
          name: userName,
          surname: userSurname,
          password: pwd,
        }),
        {
          headers: { 'Content-Type': 'application/json' },
          withCredentials: true,
        }
      );
      // TODO: remove console.logs before deployment
      console.log(JSON.stringify(response?.data));
      //console.log(JSON.stringify(response))
      setSuccess(true);
      //clear state and controlled inputs
      setUserName('');
      setUserSurname('');
      setEmail('');
      setPwd('');
      setMatchPwd('');

      // navigate(from, { replace: true });
    } catch (err) {
      if (!err?.response) {
        setErrMsg('No Server Response');
      } else if (err.response?.status === 409 || err.response?.status === 422) {
        setErrMsg('This email is already taken');
      } else {
        setErrMsg('Registration Failed');
      }
      errRef.current.focus();
    }
  };

  // TODO: remove aria attributes
  return (
    <section>
      <div className="flex flex-col items-center justify-center px-5 py-6 mx-auto">
        <div className="flex items-center mb-2 text-2xl font-semibold text-gray-900">
          <img className="w-8 h-8 mr-2" src="https://www.svgrepo.com/show/354380/spring-icon.svg" alt="logo" />
          Social Network
        </div>

        <div className="w-full bg-white rounded-lg shadow-lg md:mt-3 sm:max-w-md xl:p-0">
          <div className="p-5 space-y-3 md:space-y-4 sm:p-7">


            {success ? (
              <section>
                <h1 className="text-xl font-bold tracking-tight text-gray-900">Registration successful!</h1>
                <p className="text-sm font-light text-gray-500">
                  You may now sign into your account. {' '}
                  <Link to="/login" className="font-medium text-indigo-600 hover:underline">
                    Login here
                  </Link>
                </p>
              </section>
            ) : (
              <section>
                <h1 className="text-xl font-bold tracking-tight text-gray-900">Create new account</h1>
                {/* errMsg */}
                <p ref={errRef} className={errMsg ? 'errmsg' : 'offscreen'} aria-live="assertive">
                  {errMsg}
                </p>

                <form onSubmit={handleSubmit} className="space-y-2 md:space-y-4">
                  {/* Name */}
                  <div>
                    <label htmlFor="name" className="form-label">
                      Name
                      <span className={validUserName ? 'valid' : 'hide'}>
                        <FontAwesomeIcon icon={faCheck} />
                      </span>
                      <span className={validUserName || !userName ? 'hide' : 'invalid'}>
                        <FontAwesomeIcon icon={faTimes} />
                      </span>
                    </label>
                    <input
                      className="form-field"
                      type="text"
                      id="name"
                      ref={userRef} // user focus on input field
                      placeholder="Jan"
                      onChange={(e) => setUserName(e.target.value)}
                      required
                      aria-invalid={validUserName ? 'false' : 'true'}
                      aria-describedby="uidnote"
                      onFocus={() => setUserNameFocus(true)}
                      onBlur={() => setUserNameFocus(false)}
                    />
                    <p
                      id="uidnote"
                      className={userNameFocus && userName && !validUserName ? 'instructions' : 'offscreen'}
                    >
                      <FontAwesomeIcon icon={faInfoCircle} />4 to 24 character.
                      <br />
                      Must begin with a letter.
                      <br />
                      Letters, numbers, underscores are allowed.
                    </p>
                  </div>

                  {/* Surname */}
                  <div>
                    <label htmlFor="surname" className="form-label">
                      Surname
                      <span className={validUserSurname ? 'valid' : 'hide'}>
                        <FontAwesomeIcon icon={faCheck} />
                      </span>
                      {/* if name is not valid and if the user field is empty */}
                      {/* TODO: check for additional fields*/}
                      <span className={validUserSurname || !userSurname ? 'hide' : 'invalid'}>
                        <FontAwesomeIcon icon={faTimes} />
                      </span>
                    </label>
                    <input
                      className="form-field"
                      type="text"
                      id="surname"
                      // ref={userRef}
                      placeholder="Testowy"
                      onChange={(e) => setUserSurname(e.target.value)}
                      required
                      aria-invalid={validUserSurname ? 'false' : 'true'}
                      aria-describedby="uidnote"
                      onFocus={() => setUserSurnameFocus(true)}
                      onBlur={() => setUserSurnameFocus(false)}
                    />
                    <p
                      id="uidnote"
                      className={userSurameFocus && userSurname && !validUserSurname ? 'instructions' : 'offscreen'}
                    >
                      <FontAwesomeIcon icon={faInfoCircle} />
                      4 to 24 character.
                      <br />
                      Must begin with a letter.
                      <br />
                      Letters, numbers, underscores are allowed.
                    </p>
                  </div>

                  {/* Email */}
                  <div>
                    <label htmlFor="email" className="form-label">
                      Email
                      <span className={validEmail ? 'valid' : 'hide'}>
                        <FontAwesomeIcon icon={faCheck} />
                      </span>
                      <span className={validEmail || !email ? 'hide' : 'invalid'}>
                        <FontAwesomeIcon icon={faTimes} />
                      </span>
                    </label>
                    <input
                      className="form-field"
                      type="email"
                      id="email"
                      // ref={userRef}
                      autoComplete="email"
                      placeholder="email@mail.com"
                      onChange={(e) => setEmail(e.target.value)}
                      required
                      aria-invalid={validEmail ? 'false' : 'true'}
                      aria-describedby="uidnote"
                      onFocus={() => setEmailFocus(true)}
                      onBlur={() => setEmailFocus(false)}
                    />
                    <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
                      <FontAwesomeIcon icon={faInfoCircle} />
                      Enter a valid email.
                      <br />
                      E.g. email@mail.com
                    </p>
                  </div>

                  {/* Pwd */}
                  <div>
                    <label htmlFor="password" className="form-label">
                      Password
                      <span className={validPwd ? 'valid' : 'hide'}>
                        <FontAwesomeIcon icon={faCheck} />
                      </span>
                      {/* if pwd is not valid and if the pwd field is empty */}
                      <span className={validPwd || !pwd ? 'hide' : 'invalid'}>
                        <FontAwesomeIcon icon={faTimes} />
                      </span>
                    </label>
                    <input
                      className="form-field"
                      type="password"
                      id="password"
                      placeholder="••••••••"
                      autoComplete="off"
                      onChange={(e) => setPwd(e.target.value)}
                      required
                      aria-invalid={validPwd ? 'false' : 'true'}
                      aria-describedby="pwdnote"
                      onFocus={() => setPwdFocus(true)}
                      onBlur={() => setPwdFocus(false)}
                    />
                    <p id="pwdnote" className={pwdFocus && !validPwd ? 'instructions' : 'offscreen'}>
                      <FontAwesomeIcon icon={faInfoCircle} />
                      8 to 24 character.
                      <br />
                      Must include uppercase and lowercase letters, a number and a special character.
                      <br />
                      Allowed special characters:
                      <span aria-label="exclamation mark">!</span> <span aria-label="at symbol">@</span>{' '}
                      <span aria-label="hashtag">#</span> <span aria-label="dollar sign">$</span>{' '}
                      <span aria-label="percent">%</span>
                    </p>
                  </div>

                  {/* Confirm pwd */}
                  <div>
                    <label htmlFor="confirm_pwd" className="form-label">
                      Confirm Password
                      <span className={validMatch && matchPwd ? 'valid' : 'hide'}>
                        <FontAwesomeIcon icon={faCheck} />
                      </span>
                      <span className={validMatch || !matchPwd ? 'hide' : 'invalid'}>
                        <FontAwesomeIcon icon={faTimes} />
                      </span>
                    </label>
                    <input
                      className="form-field"
                      type="password"
                      id="confirm_pwd"
                      placeholder="••••••••"
                      autoComplete="off"
                      onChange={(e) => setMatchPwd(e.target.value)}
                      required
                      aria-invalid={validMatch ? 'false' : 'true'}
                      aria-describedby="confirmnote"
                      onFocus={() => setMatchFocus(true)}
                      onBlur={() => setMatchFocus(false)}
                    />
                    <p id="confirmnote" className={matchFocus && !validMatch ? 'instructions' : 'offscreen'}>
                      <FontAwesomeIcon icon={faInfoCircle} />
                      Must match the password you entered above.
                    </p>
                  </div>

                  {/* Button */}
                  <button
                    disabled={!validEmail || !validPwd || !validMatch ? true : false}
                    className={!validEmail || !validPwd || !validMatch ? 'form-button-inactive' : 'form-button'}
                  ></button>

                  <p className="text-sm font-light text-gray-500">
                    Have an account?{' '}
                    <Link to="/login" className="font-medium text-indigo-600 hover:underline">
                      Login here
                    </Link>
                  </p>
                </form>
              </section>
            )}
          </div>
        </div>
      </div>
    </section>
  );
};

export default Register;

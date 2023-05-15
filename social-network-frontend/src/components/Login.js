import React, { useContext, useEffect, useRef, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import axios from '../api/axios';
import useAuth from '../hooks/useAuth';

const LOGIN_URL = '/login';

const Login = () => {
  const { setAuth } = useAuth();

  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || '/';

  const userRef = useRef();
  const errRef = useRef();

  const [email, setEmail] = useState('');
  const [pwd, setPwd] = useState('');
  const [errMsg, setErrMsg] = useState('');

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg();
  }, [email, pwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(LOGIN_URL, JSON.stringify({ email: email, password: pwd }), {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true,
      });
      console.log(JSON.stringify(response?.data));
      console.log(email, pwd);

      const accessToken = response?.data?.token;
      // const roles = response?.data?.roles;

      setAuth({ email, pwd, accessToken });
      // setAuth({ email: email, password: pwd });
      setEmail('');
      setPwd('');
      navigate(from, { replace: true });
    } catch (err) {
      if (!err?.response) {
        setErrMsg('No Server Response');
      } else if (err.response?.status === 400) {
        setErrMsg('Missing Email of Password');
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorized');
      } else {
        setErrMsg('Login Failed');
      }
      errRef.current.focus();
    }
  };

  return (
    <section>
      <div className="flex flex-col items-center justify-center px-5 py-6 mx-auto">
        <div class="flex items-center mb-2 text-2xl font-semibold text-gray-900">
          <img class="w-8 h-8 mr-2" src="https://www.svgrepo.com/show/354380/spring-icon.svg" alt="logo" />
          Social Network
        </div>

        <div class="w-full bg-white rounded-lg shadow-lg md:mt-3 sm:max-w-md xl:p-0">
          <div class="p-5 space-y-3 md:space-y-4 sm:p-7">
            <h1 class="text-xl font-bold tracking-tight text-gray-900">Sign in to your account</h1>
            <section>
              <p ref={errRef} className={errMsg ? 'errmsg' : 'offscreen'} aria-live="assertive">
                {errMsg}
              </p>
              <form onSubmit={handleSubmit} className="space-y-2 md:space-y-4">
                {/* Email */}
                <div>
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <input
                    className="form-field"
                    type="text"
                    id="email"
                    placeholder="email@mail.com"
                    ref={userRef}
                    autoComplete="email"
                    onChange={(e) => setEmail(e.target.value)}
                    value={email}
                    required
                  />
                </div>

                {/* Pwd */}
                <div>
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    className="form-field"
                    type="password"
                    id="password"
                    placeholder="••••••••"
                    onChange={(e) => setPwd(e.target.value)}
                    value={pwd}
                    required
                  />
                </div>

                {/* Button */}
                <button className="form-button-login">Sign In</button>

                {/* Sign up prompt */}
                <p className="text-sm font-light text-gray-500">
                  Need an account?{' '}
                  <Link to="/register" className="font-medium text-indigo-600 hover:underline">
                    Register here
                  </Link>
                </p>
              </form>
            </section>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;

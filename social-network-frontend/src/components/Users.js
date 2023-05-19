import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import useAxiosPrivate from '../hooks/useAxiosPrivate';
import useRefreshToken from '../hooks/useRefreshToken';

const Users = () => {
  const [users, setUsers] = useState();
  const axiosPrivate = useAxiosPrivate();
  const refresh = useRefreshToken();
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getUsers = async () => {
      try {
        const response = await axiosPrivate.get('/users/recent', {
          signal: controller.signal
        });
        const userEmails = response.data.map(user => user.email);
        console.log(response.data);
        isMounted && setUsers(userEmails);
      } catch (err) {
        console.error(err);
        navigate('/login', { state: { from: location }, replace: true });
      }
    };

    getUsers();

    return () => {
      isMounted = false;
      isMounted && controller.abort();
    };
  }, []);

  return (
    <article>
      <h2>Users List</h2>
      {users?.length
        ? (
          <ul>
            {users.map((user, i) => <li key={i}>{user}</li>)}
          </ul>
        ) : <p>No users to display</p>
      }
      <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' onClick={() => refresh()}>Refresh</button>
      <br />
    </article>
  );
};

export default Users;

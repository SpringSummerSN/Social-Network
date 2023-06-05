import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import useAxiosPrivate from '../hooks/useAxiosPrivate';

const Users = () => {
  const [users, setUsers] = useState();
  const axiosPrivate = useAxiosPrivate();
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getUsers = async () => {
      try {
        const response = await axiosPrivate.get('/users', {
          signal: controller.signal
        });
        const userEmails = response.data._embedded.users.map(user => user.email);
        console.log(userEmails);
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
            {users.map((user, i) =>
              <li key={i}>
                {user}
              </li>)
            }
          </ul>
        ) : <p>No users to display</p>
      }
    </article>
  );
};

export default Users;

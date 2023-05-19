import jwtDecode from 'jwt-decode';
import React from 'react';
import useAuth from '../hooks/useAuth';

const Profile = () => {

  const { auth } = useAuth();

  const decoded = jwtDecode(auth?.token);
  console.log(decoded);

  return (
    <>
      <div>User email: {decoded?.sub}</div>
      <div>User token: {auth?.token}</div>
    </>
  );


};

export default Profile;

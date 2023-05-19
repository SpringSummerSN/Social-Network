import React from 'react';
import useAuth from '../hooks/useAuth';

const Profile = () => {

  const { auth } = useAuth();

  return (
    <>
      <div>User email: {auth?.email}</div>
      <div>User token: {auth?.token}</div>
    </>
  );


};

export default Profile;

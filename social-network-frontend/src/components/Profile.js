import jwtDecode from 'jwt-decode';
import React from 'react';
import useAuth from '../hooks/useAuth';

const Profile = () => {

  const { auth } = useAuth();

  const decoded = jwtDecode(auth?.token);

  return (
    <section>
      <div> User email: {decoded?.sub}</div >
    </section >
  );


};

export default Profile;

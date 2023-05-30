import { UserCircleIcon } from '@heroicons/react/24/outline';
import jwtDecode from 'jwt-decode';
import React from 'react';
import useAuth from '../hooks/useAuth';

const Profile = () => {

  const { auth } = useAuth();

  const decoded = jwtDecode(auth?.token);

  return (
    <section>
      <div className='flex flex-row justify-start items-center mb-3'>
        <UserCircleIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your profile</span>
      </div>
      <div> User email: {decoded?.sub}</div >
    </section >
  );


};

export default Profile;

import React from 'react';
import { Outlet } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import Navbar from './Navbar';

const Layout = () => {
  const { auth } = useAuth();
  return (
    <main className="App">
      {auth?.token && <Navbar />}
      <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <Outlet />
      </div>
    </main>
  );
};

export default Layout;

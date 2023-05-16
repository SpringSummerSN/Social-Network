import React from 'react';
import { Outlet } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import Navbar from './Navbar';

const Layout = () => {
  const { auth } = useAuth();
  return (
    <main className="App">
      {auth?.token && <Navbar />}
      <Outlet />
    </main>
  );
};

export default Layout;

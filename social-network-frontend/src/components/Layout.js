import React from 'react';
import { Outlet } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import Footer from './Footer';
import Navbar from './Navbar';

const Layout = () => {
  const { auth } = useAuth();

  return (
    <main className="App">
      {auth?.token && <Navbar />}

      <div className="layout-container">
        <Outlet />
      </div>

      {auth?.token && <Footer />}
    </main>
  );
};

export default Layout;

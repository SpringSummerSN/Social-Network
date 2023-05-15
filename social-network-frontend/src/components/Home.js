import { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthContext from '../context/AuthProvider';

const Home = () => {
  const { setAuth } = useContext(AuthContext);
  const navigate = useNavigate();

  const logout = async () => {
    // if used in more components, this should be in context
    // axios to /logout endpoint
    setAuth({});
    navigate('/linkpage');
  };

  return <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">This is Homepage</div>;
};

export default Home;

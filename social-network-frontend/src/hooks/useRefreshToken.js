import axios, { axiosPrivate } from '../api/axios';
import useAuth from './useAuth';
import useLocalStorage from './useLocalStorage';

const useRefreshToken = () => {
  const { auth, setAuth } = useAuth();
  const [persist] = useLocalStorage('persist', false);

  const refresh = async () => {
    const response = await axios.get('/refreshtoken', {
      withCredentials: true,
      headers: {
        'Authorization': `Bearer ${persist ? localStorage.getItem('token') : auth.token}`
        // 'Authorization': `Bearer ${auth.token}`
      }
    });
    // .catch(function (error) {
    //   console.log(error);
    // });

    setAuth(prev => {
      console.log("Old token: " + prev.token);
      console.log("New token: " + response.data.token);
      return {
        ...prev,
        // role: response.data.role,
        token: response.data.token
      };
    });

    if (persist)
      localStorage.setItem('token', response.data.token);
    else
      localStorage.removeItem('token');

    return response.data.token;
  };

  return refresh;
};

export default useRefreshToken;

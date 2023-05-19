import axios, { axiosPrivate } from '../api/axios';
import useAuth from './useAuth';

const useRefreshToken = () => {
  const { setAuth } = useAuth();

  const refresh = async () => {
    const response = await axios.get('/refreshtoken', {
      withCredentials: true,
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
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

    localStorage.setItem('token', response.data.token);
    return response.data.token;
  };

  return refresh;
};

export default useRefreshToken;

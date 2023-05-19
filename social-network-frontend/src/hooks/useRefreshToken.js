import axios, { axiosPrivate } from '../api/axios';
import useAuth from './useAuth';

const useRefreshToken = () => {
  const { auth, setAuth } = useAuth();

  const refresh = async () => {
    const response = await axiosPrivate.post('/refreshtoken', { token: auth.token }, {
      withCredentials: true,
    }).catch(function (error) {
      console.log(error);
    });

    setAuth(prev => {
      console.log("Old token: " + prev.token);
      console.log("New token: " + response.data.token);
      return {
        ...prev,
        // role: response.data.role,
        token: response.data.token
      };
    });
    return response.data.token;
  };
  return refresh;
};

export default useRefreshToken;

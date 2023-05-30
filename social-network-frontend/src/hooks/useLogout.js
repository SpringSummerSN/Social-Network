import axios from "../api/axios";
import useAuth from "./useAuth";

const useLogout = () => {
  const { setAuth } = useAuth();

  const logout = async () => {
    setAuth({});
    localStorage.removeItem('token');
    localStorage.removeItem('persist');
  };

  return logout;
};

export default useLogout;

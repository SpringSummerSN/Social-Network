import jwtDecode from 'jwt-decode';
import useAuth from '../hooks/useAuth';
import useRefreshToken from '../hooks/useRefreshToken';

const Admin = () => {
  const { auth } = useAuth();
  const refresh = useRefreshToken();

  const decoded = jwtDecode(auth?.token);

  return (
    <section>

      <div> User email: {decoded?.sub}</div >
      <div>User token: {auth?.token}</div>

      <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' onClick={() => refresh()}>
        Refresh
      </button>

    </section>
  );
};

export default Admin;

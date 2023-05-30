import { ShieldExclamationIcon } from '@heroicons/react/24/outline';
import jwtDecode from 'jwt-decode';
import useAuth from '../hooks/useAuth';
import useRefreshToken from '../hooks/useRefreshToken';

const Admin = () => {
  const { auth } = useAuth();
  const refresh = useRefreshToken();

  const decoded = jwtDecode(auth?.token);

  return (
    <section>
      <div className='flex flex-row justify-start items-center mb-3'>
        <ShieldExclamationIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Admin page</span>
      </div>

      <div> User email: {decoded?.sub}</div >
      <div>User token: {auth?.token}</div>

      <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' onClick={() => refresh()}>
        Refresh
      </button>

    </section>
  );
};

export default Admin;

import { Link } from 'react-router-dom';

const LinkPage = () => {
  return (
    <section>
      <div className="flex flex-col items-center justify-center px-5 py-6 mx-auto">
        <Link to="/" className="flex items-center mb-2 text-2xl font-semibold text-gray-900">
          <img className="w-8 h-8 mr-2" src="https://www.svgrepo.com/show/354380/spring-icon.svg" alt="logo" />
          Social Network
        </Link>

        <div className="w-full bg-white rounded-lg shadow-lg md:mt-3 sm:max-w-md xl:p-0">
          <div className="p-5 space-y-3 md:space-y-4 sm:p-7">
            <h1 className="text-2xl font-extrabold tracking-tight text-gray-900">Links</h1>

            <h2 className="text-xl font-bold tracking-tight text-gray-800">Public:</h2>
            <ul className="list-disc list-inside">
              <li className="list-item">
                <Link to="/login">Login</Link>
              </li>
              <li className="list-item">
                <Link to="/register">Register</Link>
              </li>
            </ul>

            <h2 className="text-xl font-bold tracking-tight text-gray-800">Private</h2>
            <ul className="list-disc list-inside">
              <li className="list-item">
                <Link to="/">Home</Link>
              </li>
              <li className="list-item">
                <Link to="/admin">Admin Page</Link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  );
};

export default LinkPage;

import { Link } from 'react-router-dom';

const Missing = () => {
  return (
    <section className="flex flex-col w-screen h-screen justify-center items-center space-y-6">
      <div class="flex items-center mb-2 text-2xl font-semibold text-gray-900">
        <img class="w-8 h-8 mr-2" src="https://www.svgrepo.com/show/354380/spring-icon.svg" alt="logo" />
        Social Network
      </div>
      <h1 className="text-9xl font-bold text-indigo-600">Oops!</h1>
      <p className="text-5xl font-medium text-gray-500">Page doesn't exist</p>
      <Link to="/" className="py-10 text-2xl font-medium font-medium text-indigo-600 hover:underline">
        Visit Our Homepage
      </Link>
    </section>
  );
};

export default Missing;

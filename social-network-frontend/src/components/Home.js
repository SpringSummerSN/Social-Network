import { NewspaperIcon } from '@heroicons/react/24/outline';
import Posts from './Posts';


const Home = () => {
  return (
    <div className='space-y-4'>
      <div className='flex flex-row justify-start items-center mb-3'>
        <NewspaperIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your feed</span>
      </div>

      <Posts />

    </div>
  );
};

export default Home;

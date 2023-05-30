import { MegaphoneIcon } from '@heroicons/react/24/outline';
import React from 'react';

const Groups = () => {
  return (
    <div>
      <div className='flex flex-row justify-start items-center mb-3'>
        <MegaphoneIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your groups</span>
      </div>
    </div>
  );
};

export default Groups;

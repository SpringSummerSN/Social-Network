import { Cog6ToothIcon } from '@heroicons/react/24/outline';
import React from 'react';

const Settings = () => {
  return (
    <div>
      <div className='flex flex-row justify-start items-center mb-3'>
        <Cog6ToothIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Settings</span>
      </div>
    </div>
  );
};

export default Settings;

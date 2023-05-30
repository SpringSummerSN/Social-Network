import { UsersIcon } from '@heroicons/react/24/outline';
import React from 'react';
import Users from './Users';

const Friends = () => {
  return (
    <div>
      <div className='flex flex-row justify-start items-center mb-3'>
        <UsersIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your friends</span>
      </div>
      <Users />
    </div>
  );
};

export default Friends;

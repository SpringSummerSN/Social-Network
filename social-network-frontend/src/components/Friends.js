import { ChatBubbleBottomCenterTextIcon, EllipsisHorizontalCircleIcon, EllipsisVerticalIcon, UserMinusIcon, UsersIcon } from '@heroicons/react/24/outline';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import useAxiosPrivate from '../hooks/useAxiosPrivate';

const Friends = () => {

  const [friends, setFriends] = useState();
  const axiosPrivate = useAxiosPrivate();
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getFriends = async () => {
      try {
        const response = await axiosPrivate.get('/users', {
          signal: controller.signal
        });
        const allFriends = response.data._embedded.users;
        console.log(allFriends);
        isMounted && setFriends(allFriends);
      } catch (err) {
        console.error(err);
        // navigate('/login', { state: { from: location }, replace: true });
      }
    };

    getFriends();

    return () => {
      isMounted = false;
      isMounted && controller.abort();
    };
  }, []);

  return (
    <div>
      <div className='flex flex-row justify-start items-center mb-3'>
        <UsersIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your friends</span>
      </div>
      {friends?.length ?
        (
          <div className='card-contatiner'>
            {friends.map((friend, i) =>
              <div className='card-item'>
                <div className='card'>
                  <img
                    className="h-36 w-36 rounded-full shadow-lg cursor-pointer"
                    src="/assets/profile-picture-1.jpg"
                    alt="logo"
                  />
                  <div className='friend-info'>
                    <span className='cursor-pointer'>
                      {friend.name + ' ' + friend.surname}
                    </span>
                    <span className='text-sm text-gray-400 font-light'>
                      {friend.email}
                    </span>
                  </div>
                  <div className='friend-actions'>
                    <ChatBubbleBottomCenterTextIcon className='w-8 h-8 cursor-pointer' />
                    <UserMinusIcon className='w-8 h-8 cursor-pointer' />
                    <EllipsisHorizontalCircleIcon className='w-8 h-8 cursor-pointer' />
                  </div>
                </div>
              </div>)}
          </div>

        ) :
        <div className='font-black text-2xl'>
          No friends to display.
        </div>
      }
    </div>
  );
};

export default Friends;

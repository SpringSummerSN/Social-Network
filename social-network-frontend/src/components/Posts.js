import { ChatBubbleBottomCenterTextIcon, MapPinIcon, PencilSquareIcon } from '@heroicons/react/20/solid';
import { HandThumbDownIcon, HandThumbUpIcon } from '@heroicons/react/24/outline';
import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import useAxiosPrivate from '../hooks/useAxiosPrivate';

const Posts = () => {
  const [isFetching, setIsFetching] = useState(true);
  const [posts, setPosts] = useState();
  const axiosPrivate = useAxiosPrivate();
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setTimeout(function () {
      setIsFetching(false);
    }, 1000);
  }, []);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    const getPosts = async () => {
      try {
        const response = await axiosPrivate.get('/posts', {
          signal: controller.signal
        });
        const allPosts = response.data;
        console.log(allPosts);
        isMounted && setPosts(allPosts);
      } catch (err) {
        console.error(err);
        // navigate('/login', { state: { from: location }, replace: true });
      }
    };

    getPosts();

    return () => {
      isMounted = false;
      isMounted && controller.abort();
    };
  }, []);

  if (isFetching) {
    return (
      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <CircularProgress color='success' />
      </Box>
    );
  }

  return (
    <>
      {posts?.length ?
        (
          <>
            {posts.map((post, i) =>
              <div className='post-container'>
                <div className='content-section'>

                  <div className='post-info'>
                    <img
                      className="h-10 w-10 rounded-full mr-2"
                      src="/assets/profile-picture-1.jpg"
                      alt="logo"
                    />
                    <div className='post-author'>
                      <div className='flex flex-row items-baseline'>
                        <span className='text-lg font-semibold text-gray-900'>{post.creator.name + ' ' + post.creator.surname}</span>
                        <span className='ml-2 text-xs font-light text-gray-400'>{post.creator.email}</span>
                      </div>
                      <div className='flex flex-row items-center justify-start'>
                        <MapPinIcon className='w-3 h-3 mr-1' />
                        <span className='text-xs font-light text-gray-500'>Java Mountains</span>
                      </div>
                    </div>
                  </div>

                  <div className='post-content'>
                    <img
                      className='w-full rounded-md'
                      src='https://www.telluride.com/site/assets/files/37120/mv.2200x1032.webp'
                      alt='mountain'
                    />
                    <span>
                      {post.description}
                    </span>
                  </div>

                  <div className='post-actions'>
                    <HandThumbUpIcon type='button' className='w-8 h-8 cursor-pointer text-green-500' onClick={() => { alert("You upvoted this post!"); }} />

                    <span className='text-lg font-bold'>{post.likes.length}</span>

                    <HandThumbDownIcon type='button' className='w-8 h-8 cursor-pointer text-indigo-500' onClick={() => { alert("You downvoted this post!"); }} />

                    <input type="text" className="comment-input" placeholder="Enter your comment" />

                    <PencilSquareIcon type="button" className="comment-button" />

                  </div>

                </div>

                <div className='comment-section'>
                  <div className='flex flex-row items-center mb-3'>
                    <ChatBubbleBottomCenterTextIcon className='w-6 h-6 mr-2' />
                    <span className='text-lg font-bold'>Comments</span>
                  </div>

                  <div className='comments'>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is comment bla bla bla bla...</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                    <div className='comment'>
                      <div className='comment-avatar'>
                        <img
                          className="h-8 w-8 rounded-full mr-2"
                          src="/assets/profile-picture-1.jpg"
                          alt="logo"
                        />
                      </div>

                      <div className='comment-content'>
                        <span className='font-light text-xs text-gray-500'>Name Surname</span>
                        <span className='text-gray-900'>This is another long comment bla bla bla bla bla bla lsdfhjksadhlkhgasjblhagskbhagsbgha bhga bh gashb</span>
                      </div>
                    </div>

                  </div>

                </div>
              </div>)
            }
          </>
        )
        :
        <div className='font-black text-2xl'>
          No posts to display.
        </div>
      }
    </>
  );
};

export default Posts;

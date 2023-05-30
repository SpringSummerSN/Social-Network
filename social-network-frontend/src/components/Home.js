import { ArrowDownIcon, ArrowUpIcon, ChatBubbleBottomCenterTextIcon, MapPinIcon, PencilSquareIcon } from '@heroicons/react/20/solid';
import { NewspaperIcon } from '@heroicons/react/24/outline';
import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../context/AuthProvider';
import useLogout from '../hooks/useLogout';

const Home = () => {
  const navigate = useNavigate();
  const logout = useLogout();

  const signOut = async () => {
    await logout();
    navigate('/linkpage');
  };

  return (
    <div className='space-y-4'>
      <div className='flex flex-row justify-start items-center mb-3'>
        <NewspaperIcon className='w-8 h-8 mr-2' />
        <span className='text-xl font-bold'>Your feed</span>
      </div>

      {/* Example post n1 */}
      <div className='post-container'>
        <div className='content-section'>

          <div className='post-info'>
            <img
              className="h-10 w-10 rounded-full mr-2"
              src="/assets/profile-picture-1.jpg"
              alt="logo"
            />
            <div className='post-author'>
              <span className='text-lg font-semibold text-gray-900'>John Spring</span>
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
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris nec justo vel lacus porttitor fringilla. Morbi pellentesque, augue nec varius luctus, neque nisi accumsan nulla, sit amet rutrum nunc felis ac eros. Nullam nec ultricies erat. Nunc eu laoreet risus. Maecenas luctus id ipsum ac viverra. Aenean facilisis vulputate lorem sed feugiat. Pellentesque tempor mattis quam, eget sodales quam semper non. Aenean orci augue, euismod at varius a, suscipit non leo. Praesent viverra vulputate lectus eget eleifend. Aenean vel ultrices erat. Maecenas bibendum facilisis ultricies. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin nec auctor nisl, non volutpat nisi.
          </div>

          <div className='post-actions'>
            <ArrowUpIcon type='button' className='w-8 h-8 cursor-pointer text-green-500' onClick={() => { alert("You upvoted this post!"); }} />

            <span className='text-lg font-bold'>19</span>

            <ArrowDownIcon type='button' className='w-8 h-8 cursor-pointer text-indigo-500' onClick={() => { alert("You downvoted this post!"); }} />

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
      </div>

    </div>
  );
};

export default Home;

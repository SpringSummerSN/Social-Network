import { HeartIcon } from "@heroicons/react/20/solid";

const Footer = () => {
  return (
    <div className="footer-container">
      <div className="flex flex-col w-full items-center text-gray-400 font-medium text-sm">
        <div className="flex flex-col justify-center items-center">
          <span className="text-center">Social Network application written in Spring Boot and ReactJS.</span>
          <span className="text-center">This project was created by Damian Tomczyszyn, Dmytro Nimchynskyi, Tomasz Korniszuk, Laura Wołoszun & Jakub Grusiewicz.</span>
        </div>
        <div className="flex flex-row items-center">
          <span>Made with</span>
          <HeartIcon className='w-5 h-5 text-red-400 ml-1' />
        </div>
      </div>
    </div>
  );
};

export default Footer;

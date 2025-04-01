import React, {useEffect} from 'react';
import useAuth from '../hook/useAuth';


const TopMenu = () => {
  const { user } = useAuth();

  console.log(user);
  return (
    <nav className="bg-black p-4">
      <div className="container mx-auto flex justify-between items-center">
        {/* Logo */}
        <div className="text-white font-bold text-xl">
          MyApp
        </div>

        {/* Desktop Navigation */}
        <div className="hidden md:flex space-x-6">
          <a href="/home" className="text-gray-300 hover:text-white">Home</a>
          {user ? (
            // 사용자가 로그인한 상태일 때 보여줄 메뉴
            <>
              <span className="text-gray-300">안녕하세요, {user.username}님</span>
              <a href="/profile" className="text-gray-300 hover:text-white">프로필</a>
              <a href="/logout" className="text-gray-300 hover:text-white">로그아웃</a>
            </>
          ) : (
            // 사용자가 로그인하지 않은 상태일 때 보여줄 메뉴
            <>
              <a href="/login" className="text-gray-300 hover:text-white">로그인</a>
              <a href="/register" className="text-gray-300 hover:text-white">회원가입</a>
            </>
          )}
        </div>

      </div>
    </nav>
  );
};

export default TopMenu;

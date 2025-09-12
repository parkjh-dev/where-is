import React from 'react';
import { Link } from 'react-router-dom';

function EmailLoginButton({ size = 24, className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <Link 
        to="/auth/login/email"
        onClick={handleClick}
        className= {`w-full bg-gray-200 hover:bg-gray-400 text-gray-800 py-3 px-6 rounded-lg font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 flex items-center relative border border-transparent hover:border-gray-400 ${className}`}
    >
        <img src="/src/assets/mail.png" alt="이메일" className="w-5 h-5 absolute left-6" />
        <span className="w-full text-center">이메일로 로그인</span>
    </Link>
  );
}

export default EmailLoginButton;

import React from 'react';

function EmailLoginButton({ size = 24, className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <button 
        onClick={handleClick}
        className= {`w-full bg-[#FEE500] hover:bg-[#FDD800] text-[#000000]/85 py-3 px-6 rounded-lg font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 flex items-center relative border border-transparent hover:border-[#FDD800] ${className}`}
    >
        <img src="/src/assets/kakao.png" alt="카카오" className="w-5 h-5 absolute left-6" />
        <span className="w-full text-center">카카오 로그인</span>
    </button>
  );
}

export default EmailLoginButton;
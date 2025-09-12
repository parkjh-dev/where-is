import React from 'react';

function BackButton({ size = 24, className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <img
      src="/src/assets/back.png"
      alt="뒤로가기"
      width={size}
      height={size}
      className={`cursor-pointer hover:opacity-70 transition-opacity duration-200 ${className}`}
      onClick={handleClick}
    />
  );
}

export default BackButton;
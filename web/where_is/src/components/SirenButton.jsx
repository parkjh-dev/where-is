import React from 'react';

function SirenButton({ size = 0, className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <img 
        src="/src/assets/siren.png" 
        alt="잘못된 정보 제보" 
        className={`w-6 h-6 cursor-pointer hover:opacity-70 transition-opacity duration-200 ${className}`}
        onClick={handleClick}
    />
  );
}

export default SirenButton;





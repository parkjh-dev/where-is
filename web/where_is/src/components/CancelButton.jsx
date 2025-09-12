import React from 'react';

function CancelButton({ size = 0, className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <div 
        className={`flex items-center space-x-2 cursor-pointer hover:opacity-70 transition-opacity duration-200 ${className}`}
        onClick={handleClick}
    >
        <img src="/src/assets/cancel.png" alt="닫기" className="w-6 h-6" />
    </div>
  );
}

export default CancelButton;








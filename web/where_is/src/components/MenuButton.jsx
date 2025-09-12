import React from 'react';

function MenuButton({ className = '', onClick }) {
  const handleClick = onClick || (() => window.history.back());

  return (
    <div 
        className={`flex items-center space-x-2 cursor-pointer hover:opacity-70 transition-opacity duration-200 ${className}`}
        onClick={handleClick}
    >
        <img src="/src/assets/menu.png" alt="메뉴" className="w-6 h-6" />
    </div>
  );
}

export default MenuButton;
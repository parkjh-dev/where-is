import React from 'react'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <header className="bg-white border-b border-gray-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-10 flex items-center justify-between">
        <div 
          className="flex items-center space-x-2 cursor-pointer hover:opacity-70 transition-opacity duration-200"
          onClick={() => console.log('메뉴 클릭')}
        >
          <img src="/pictogram/menu.png" alt="메뉴" className="w-6 h-6" />
        </div>
        <nav className="space-x-4 text-sm">
          <Link to="/auth/login/main" className="text-gray-600 hover:text-gray-900">Login</Link>
        </nav>
      </div>
    </header>
  )
}

export default Header
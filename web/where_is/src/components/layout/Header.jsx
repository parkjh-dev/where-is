import React, { useState, useRef, useEffect } from 'react'
import MenuForm from '../MenuForm'
import LoginForm from '../auth/LoginForm'
import MenuButton from '../MenuButton'


const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const menuRef = useRef(null)
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false)

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen)
  }

  const closeMenu = () => {
    setIsMenuOpen(false)
  }

  // 메뉴 외부 클릭 시 닫기
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (menuRef.current && !menuRef.current.contains(event.target)) {
        // 오버레이 내부 클릭 시에는 닫지 않음 (패널 영역 포함)
        // 실사용에서는 더 정교한 hit-test가 필요할 수 있음
      }
    }

    if (isMenuOpen) {
      document.addEventListener('mousedown', handleClickOutside)
    }

    return () => {
      document.removeEventListener('mousedown', handleClickOutside)
    }
  }, [isMenuOpen])

  return (
    <header className="bg-white border-b border-gray-200 relative">
      <div className="w-full px-4 sm:px-6 lg:px-8 h-10 flex items-center justify-between">
        <div className="relative" ref={menuRef}>
          <MenuButton onClick={toggleMenu} />

          {/* 컨테이너 폭에 맞춘 좌측 슬라이드 모달 */}
          {isMenuOpen && (
            <MenuForm isMenuOpen={isMenuOpen} closeMenu={closeMenu} />
          )}
        </div>
        <nav className="space-x-4 text-sm">
          <a
            href="#"
            className="text-gray-600 hover:text-gray-900"
            onClick={(e) => {
              e.preventDefault()
              setIsLoginModalOpen(true)
            }}
          >
            Login
          </a>
        </nav>
      </div>

      {/* 로그인 모달 */}
      <LoginForm 
        isOpen={isLoginModalOpen}
        onClose={() => setIsLoginModalOpen(false)}
        onKakaoLogin={() => {
          console.log('카카오 로그인')
          setIsLoginModalOpen(false)
        }}
        onEmailLogin={() => {
          console.log('이메일 로그인')
          setIsLoginModalOpen(false)
        }}
      />
    </header>
  )
}

export default Header
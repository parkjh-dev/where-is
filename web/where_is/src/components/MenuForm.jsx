import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import PlaceRequestForm from './PlaceRequestForm'
import CancelButton from './CancelButton';

const MenuForm = ({ isMenuOpen, closeMenu }) => {
  const [activeMenu, setActiveMenu] = useState(null)
  const [isPlaceRequestModalOpen, setIsPlaceRequestModalOpen] = useState(false)

  const handleMenuClick = (menuName) => {
    if (activeMenu === menuName) {
      setActiveMenu(null) // 이미 활성화된 메뉴를 클릭하면 비활성화
    } else {
      setActiveMenu(menuName) // 새로운 메뉴 활성화
    }

    // 장소 등록 요청 메뉴 클릭 시 모달 열기
    if (menuName === 'place-request') {
      setIsPlaceRequestModalOpen(true)
    }
  }

  const handleClosePlaceRequestModal = () => {
    setIsPlaceRequestModalOpen(false)
  }

  return (
    <>
      <div className="fixed inset-0 z-[9999]">
        {/* 오버레이 */}
        <div className="absolute inset-0 bg-black bg-opacity-50" onClick={closeMenu} />

        {/* BasicLayout과 동일한 최대폭 컨테이너 */}
        <div className="relative h-full mx-auto w-full max-w-[360px] sm:max-w-[380px] md:max-w-[400px] lg:max-w-[480px]">
          {/* 헤더 높이만큼 여백 (h-10) */}
          <div className={`absolute left-0 top-10 bottom-0 w-2/3 bg-white shadow-lg border border-gray-200 rounded-r-xl overflow-y-auto transform transition-transform duration-300 ease-out ${
            isMenuOpen ? 'translate-x-0 opacity-100' : '-translate-x-full opacity-0'
          }`}>
            <div className="p-4 sm:p-6">
              <div className="flex items-center justify-between mb-6 sm:mb-8">
                <h2 className="text-lg sm:text-xl font-bold text-gray-800">메뉴</h2>
                <CancelButton onClick={closeMenu} />
              </div>

              <nav className="space-y-2">
                <div 
                  onClick={() => handleMenuClick('place-request')}
                  className={`block w-full text-left px-3 py-3 sm:px-4 sm:py-4 rounded-lg transition-colors duration-200 cursor-pointer ${
                    activeMenu === 'place-request' 
                      ? 'bg-gray-200 text-gray-900' 
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  장소 등록 요청
                </div>

                <div className="border-t border-gray-200 my-4 sm:my-6"></div>

                <div 
                  onClick={() => handleMenuClick('mypage')}
                  className={`block w-full text-left px-3 py-3 sm:px-4 sm:py-4 rounded-lg transition-colors duration-200 cursor-pointer ${
                    activeMenu === 'mypage' 
                      ? 'bg-gray-200 text-gray-900' 
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  마이페이지
                </div>
                <div 
                  onClick={() => handleMenuClick('settings')}
                  className={`block w-full text-left px-3 py-3 sm:px-4 sm:py-4 rounded-lg transition-colors duration-200 cursor-pointer ${
                    activeMenu === 'settings' 
                      ? 'bg-gray-200 text-gray-900' 
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  설정
                </div>
                <div 
                  onClick={() => handleMenuClick('help')}
                  className={`block w-full text-left px-3 py-3 sm:px-4 sm:py-4 rounded-lg transition-colors duration-200 cursor-pointer ${
                    activeMenu === 'help' 
                      ? 'bg-gray-200 text-gray-900' 
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  도움말
                </div>

                <div className="border-t border-gray-200 my-4 sm:my-6"></div>

                <div 
                  onClick={() => handleMenuClick('logout')}
                  className={`block w-full text-left px-3 py-3 sm:px-4 sm:py-4 rounded-lg transition-colors duration-200 cursor-pointer ${
                    activeMenu === 'logout' 
                      ? 'bg-red-100 text-red-800' 
                      : 'text-red-600 hover:bg-red-50'
                  }`}
                >
                  로그아웃
                </div>
              </nav>
            </div>
          </div>
        </div>
      </div>

      {/* PlaceRequestForm 모달 */}
      {isPlaceRequestModalOpen && (
        <PlaceRequestForm onClose={handleClosePlaceRequestModal} />
      )}
    </>
  )
}

export default MenuForm
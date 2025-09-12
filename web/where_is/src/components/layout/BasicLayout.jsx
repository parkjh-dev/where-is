import React from 'react'
import Header from './Header'
import Footer from './Footer'
import AIAssistant from './AIAssistant'
import { Outlet, useLocation } from 'react-router-dom'

// 이 값을 true로 하면 전체 폭, false로 하면 고정 폭(1200px)
const isFullWidth = false

const BasicLayout = () => {
  const location = useLocation()
  const isMainPage = location.pathname === '/'
  const containerClass = isFullWidth
    ? 'mx-auto w-full'
    : 'mx-auto max-w-[360px] sm:max-w-[380px] md:max-w-[400px] lg:max-w-[480px]'

  if (isMainPage) {
    return (
      <div className="h-screen w-full flex flex-col bg-gray-50 overflow-hidden">
        <main className="flex-1 w-full">
          <div className={`${containerClass} bg-gray-100 h-full`}>
            <Outlet />
          </div>
        </main>
      </div>
    )
  }

  return (
    <div className="h-screen w-full flex flex-col bg-gray-50 overflow-hidden">
      <main className="flex-1 w-full overflow-hidden">
        <div className={`${containerClass} bg-gray-100 h-full flex flex-col`}>
          <Header />
          <div className="flex-1 overflow-y-auto scrollbar-hide">
            <Outlet />
          </div>
          <Footer />   
        </div>
      </main>
      <AIAssistant /> 
    </div>
  )
}

export default BasicLayout 
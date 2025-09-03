import React from 'react'
import { Link } from 'react-router-dom'

const LoginEmailForm = ({ 
  onKakaoLogin,
  onEmailLogin,
  onSignUp,
  onFindEmail,
  onFindPassword,
  className = ""
}) => {
  const handleSubmit = (e) => {
    e.preventDefault()
    if (onEmailLogin) {
      onEmailLogin()
    }
  }

  return (
    <div className={`space-y-8 ${className}`}>
      {/* 제목 */}
      <div className="text-center">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">로그인</h1>
        <p className="text-gray-600">Where is 계정으로 로그인하세요</p>
      </div>

      {/* 이메일 로그인 폼 */}
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">이메일</label>
          <input 
            type="email" 
            placeholder="이메일 주소를 입력하세요"
            className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-cyan-500 focus:border-cyan-500 transition-all duration-200"
            required
          />
        </div>
        
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">비밀번호</label>
          <input 
            type="password" 
            placeholder="비밀번호를 입력하세요"
            className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-cyan-500 focus:border-cyan-500 transition-all duration-200"
            required
          />
        </div>

        {/* 체크박스 */}
        <div className="flex items-center">
          <input 
            type="checkbox" 
            id="remember"
            className="w-4 h-4 text-cyan-600 border-gray-300 rounded focus:ring-cyan-500"
          />
          <label htmlFor="remember" className="ml-2 text-sm text-gray-700">
            로그인 상태 유지
          </label>
        </div>

        {/* 로그인 버튼 */}
        <button 
          type="submit"
          className="w-full bg-gray-800 hover:bg-gray-900 text-white py-3 px-4 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95"
        >
          로그인
        </button>
      </form>

      <div className="flex justify-center space-x-4 text-sm">
        <Link 
          to="/"
          className="text-gray-600 hover:text-gray-700 font-medium transition-colors duration-200"
        >
          이메일 찾기
        </Link>
        <span className="text-gray-400">|</span>
        <Link 
          to="/"
          className="text-gray-600 hover:text-gray-700 font-medium transition-colors duration-200"
        >
          비밀번호 찾기
        </Link>
      </div>

      {/* 회원가입 */}
      <div className="text-center pt-4">
        <span className="text-gray-600 text-sm">계정이 없으신가요? </span>
        <a 
          href="http://www.naver.com"
          className="text-cyan-600 hover:text-cyan-700 font-medium text-sm underline underline-offset-2 hover:no-underline transition-all duration-200"
        >회원가입</a>
      </div>
    </div>
  )
}

export default LoginEmailForm 
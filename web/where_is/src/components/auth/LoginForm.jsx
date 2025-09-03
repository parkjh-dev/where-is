import React from 'react'
import { Link } from 'react-router-dom'

const LoginForm = ({ 
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

      <div className="space-y-4">
        <button 
          onClick={onKakaoLogin}
          className="w-full bg-[#FEE500] hover:bg-[#FDD800] text-[#000000]/85 py-3 px-6 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 flex items-center relative border border-transparent hover:border-[#FDD800]"
        >
          <img src="/pictogram/kakao.png" alt="카카오" className="w-5 h-5 absolute left-6" />
          <span className="w-full text-center">카카오 로그인</span>
        </button>

        <Link 
          to="/auth/login/email"
          className="w-full bg-gray-200 hover:bg-gray-400 text-gray-800 py-3 px-6 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 flex items-center relative border border-transparent hover:border-gray-400"
        >
          <img src="/pictogram/mail.png" alt="이메일" className="w-5 h-5 absolute left-6" />
          <span className="w-full text-center">이메일로 로그인</span>
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

export default LoginForm 
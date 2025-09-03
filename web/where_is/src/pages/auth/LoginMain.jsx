import React from 'react'
import LoginForm from '../../components/auth/LoginForm'

const LoginMain = () => {
  const handleKakaoLogin = () => console.log('카카오 로그인')
  const handleEmailLogin = () => console.log('이메일 로그인')
  const handleSignUp = () => console.log('회원가입 페이지로 이동')
  const handleFindEmail = () => console.log('이메일 찾기')
  const handleFindPassword = () => console.log('비밀번호 찾기')

  return (
    <LoginForm
      onKakaoLogin={handleKakaoLogin}
      onEmailLogin={handleEmailLogin}
      onSignUp={handleSignUp}
      onFindEmail={handleFindEmail}
      onFindPassword={handleFindPassword}
    />
  )
}

export default LoginMain 
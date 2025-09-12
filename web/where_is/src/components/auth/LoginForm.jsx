import React from 'react';
import { Link } from 'react-router-dom';
import CancelButton from '../CancelButton';
import EmailLoginButton from '../EmailLoginButton';
import KakaoLoginButton from '../KakaoLoginButton';

const LoginForm = ({ 
  isOpen = false,
  onClose,
  onKakaoLogin,
  onEmailLogin,
  onSignUp,
  onFindEmail,
  onFindPassword,
  className = ""
}) => {
  const handleSubmit = (e) => {
    e.preventDefault();
    if (onEmailLogin) {
      onEmailLogin();
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-[9999]">
      {/* 오버레이 */}
      <div
        className="absolute inset-0 bg-black bg-opacity-50"
        onClick={onClose}
      />
      
      {/* 하단 슬라이드 모달 */}
      <div className="absolute inset-x-0 bottom-0 mx-auto w-full max-w-[360px] sm:max-w-[380px] md:max-w-[400px] lg:max-w-[480px] px-4 pb-4">
        <div className="bg-white rounded-t-xl shadow-lg border border-gray-200 transform transition-transform duration-300 ease-out translate-y-0 p-4 sm:p-6">
          {/* 헤더 */}
          <div className="flex items-center justify-between mb-6">
            <h2 className="text-lg sm:text-xl font-bold text-gray-800">로그인</h2>
            <CancelButton onClick={onClose} />
          </div>

          {/* 로그인 폼 */}
          <div className={`space-y-6 ${className}`}>
            <p className="text-center text-gray-600 text-sm">Where is 계정으로 로그인하세요</p>

            <div className="space-y-3">
              <KakaoLoginButton onClick={onKakaoLogin} />
              <EmailLoginButton onClick={onEmailLogin} />
            </div>

            {/* 회원가입 */}
            <div className="text-center pt-2">
              <span className="text-gray-600 text-sm">계정이 없으신가요? </span>
              <Link 
                to="/auth/login/email/register"
                className="text-cyan-600 hover:text-cyan-700 font-medium text-sm underline underline-offset-2 hover:no-underline transition-all duration-200"
              >
                회원가입
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginForm; 
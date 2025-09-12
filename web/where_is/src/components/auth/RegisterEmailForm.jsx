import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const RegisterEmailForm = ({ 
}) => {
  const [currentStep, setCurrentStep] = useState(1)
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    phoneNumber: '',
    nickname: '',
    name: '',
    profileImage: null
  })

  const steps = [
    { id: 1, title: '이메일', description: '이메일을 입력해주세요' },
    { id: 2, title: '패스워드', description: '패스워드를 설정해주세요' },
    { id: 3, title: '핸드폰 번호', description: '핸드폰 번호를 입력해주세요' },
    { id: 4, title: '프로필 정보', description: '닉네임, 이름, 프로필 사진을 입력해주세요' }
  ]

  const handleInputChange = (field, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  const handleNext = () => {
    if (currentStep < steps.length) {
      setCurrentStep(currentStep + 1)
    }
  }

  const handlePrev = () => {
    if (currentStep > 1) {
      setCurrentStep(currentStep - 1)
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    console.log('회원가입 데이터:', formData)
  }

  const isStepValid = () => {
    switch (currentStep) {
      case 1:
        return formData.email && formData.email.includes('@')
      case 2:
        return formData.password && formData.confirmPassword && formData.password === formData.confirmPassword
      case 3:
        return formData.phoneNumber && formData.phoneNumber.length >= 10
      case 4:
        return formData.nickname && formData.name
      default:
        return false
    }
  }

  const renderStepContent = () => {
    switch (currentStep) {
      case 1:
        return (
          <div className="space-y-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">이메일</label>
            <input 
              type="email" 
              placeholder="이메일을 입력하세요"
              value={formData.email}
              onChange={(e) => handleInputChange('email', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />
          </div>
        )
      
      case 2:
        return (
          <div className="space-y-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">패스워드</label>
            <input 
              type="password" 
              placeholder="패스워드를 입력하세요"
              value={formData.password}
              onChange={(e) => handleInputChange('password', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />
            <input 
              type="password" 
              placeholder="패스워드 다시 입력하세요"
              value={formData.confirmPassword}
              onChange={(e) => handleInputChange('confirmPassword', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />
            {formData.password && formData.confirmPassword && formData.password !== formData.confirmPassword && (
              <p className="text-red-500 text-sm">패스워드가 일치하지 않습니다.</p>
            )}
          </div>
        )
      
      case 3:
        return (
          <div className="space-y-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">핸드폰 번호</label>
            <input 
              type="tel" 
              placeholder="핸드폰 번호를 입력하세요(-제외)"
              value={formData.phoneNumber}
              onChange={(e) => handleInputChange('phoneNumber', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />
            <button 
              type="button"
              className="w-full bg-gray-800 hover:bg-gray-900 text-white py-3 px-4 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95"
            >
              핸드폰 인증
            </button>
          </div>
        )
      
      case 4:
        return (
          <div className="space-y-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">닉네임</label>
            <input 
              type="text" 
              placeholder="닉네임을 입력하세요"
              value={formData.nickname}
              onChange={(e) => handleInputChange('nickname', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />
            
            <label className="block text-sm font-medium text-gray-700 mb-2">이름</label>
            <input 
              type="text" 
              placeholder="이름을 입력하세요"
              value={formData.name}
              onChange={(e) => handleInputChange('name', e.target.value)}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
              required
            />

            <label className="block text-sm font-medium text-gray-700 mb-2">프로필 사진</label>
            <input 
              type="file" 
              accept="image/*"
              onChange={(e) => handleInputChange('profileImage', e.target.files[0])}
              className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
            />
          </div>
        )
      
      default:
        return null
    }
  }

  return (
    <div className="max-w-md mx-auto">
      {/* 진행 단계 표시 */}
      <div className="mb-8">
        <div className="flex justify-between items-center mb-4">
          {steps.map((step, index) => (
            <div key={step.id} className="flex flex-col items-center">
              <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-semibold ${
                currentStep >= step.id 
                  ? 'bg-gray-600 text-white' 
                  : 'bg-gray-200 text-gray-500'
              }`}>
                {step.id}
              </div>
              <span className={`text-xs mt-1 ${
                currentStep >= step.id ? 'text-gray-600' : 'text-gray-500'
              }`}>
                {step.title}
              </span>
            </div>
          ))}
        </div>
        <div className="text-center">
          <h2 className="text-lg font-semibold text-gray-800">{steps[currentStep - 1].title}</h2>
          <p className="text-sm text-gray-600">{steps[currentStep - 1].description}</p>
        </div>
      </div>

      {/* 폼 내용 */}
      <form onSubmit={handleSubmit} className="space-y-6">
        {renderStepContent()}

        {/* 네비게이션 버튼 */}
        <div className="flex space-x-3">
          {currentStep > 1 && (
            <button
              type="button"
              onClick={handlePrev}
              className="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-700 py-3 px-4 rounded-xl font-semibold transition-all duration-200"
            >
              이전
            </button>
          )}
          
          {currentStep < steps.length ? (
            <button
              type="button"
              onClick={handleNext}
              disabled={!isStepValid()}
              className={`flex-1 py-3 px-4 rounded-xl font-semibold transition-all duration-200 ${
                isStepValid()
                  ? 'bg-gray-600 hover:bg-gray-700 text-white hover:shadow-lg active:scale-95'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
              }`}
            >
              다음
            </button>
          ) : (
            <button
              type="submit"
              disabled={!isStepValid()}
              className={`flex-1 py-3 px-4 rounded-xl font-semibold transition-all duration-200 ${
                isStepValid()
                  ? 'bg-gray-800 hover:bg-gray-900 text-white hover:shadow-lg active:scale-95'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
              }`}
            >
              회원가입 완료
            </button>
          )}
        </div>
      </form>
    </div>
  )
}

export default RegisterEmailForm
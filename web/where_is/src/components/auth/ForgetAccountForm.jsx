import React from 'react'
import { Link } from 'react-router-dom'

const ForgetAccountForm = ({ 
  // props here
}) => {
  return (
    <div className="space-y-4">
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">핸드폰 번호</label>
        <input 
          type="tel" 
          placeholder="핸드폰 번호를 입력하세요(-제외)"
          value=''
          onChange=''
          className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
          required
        />
        <button 
          type="button"
          className="w-full bg-gray-800 hover:bg-gray-900 text-white py-3 px-4 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 mt-2"
        >
          핸드폰 인증
        </button>
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">인증번호</label>
        <input 
          type="text" 
          placeholder="인증번호를 입력하세요"
          value=''
          onChange=''
          className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
          required
        />
        <button 
          type="button"
          className="w-full bg-gray-800 hover:bg-gray-900 text-white py-3 px-4 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95 mt-2"
        >
          확인
        </button>
      </div>
    </div>
  )
}

export default ForgetAccountForm 
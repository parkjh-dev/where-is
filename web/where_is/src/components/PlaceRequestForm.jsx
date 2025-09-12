import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import CancelButton from './CancelButton'

const PlaceRequestForm = ({ 
  onClose
}) => {
  const [formData, setFormData] = useState({
    placeName: '',
    postalCode: '',
    address: '',
    detailAddress: '',
    coordinates: '',
    manager: '',
    contact: '',
    openHours: '',
    image: null
  })

  const handleInputChange = (field, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    console.log('장소 요청 데이터:', formData)
  }

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-[10000]">
      <div className="bg-white rounded-xl p-6 w-full max-w-sm mx-4 relative">
        {/* 헤더 */}
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-lg font-bold text-gray-800">장소 등록 요청</h2>
          <CancelButton onClick={onClose} />
        </div>

        {/* 설명 */}
        <div className="text-center mb-6">
          <p className="text-sm text-gray-600">새로운 장소를 등록해주세요</p>
        </div>

        {/* 폼 */}
        <form onSubmit={handleSubmit} className="space-y-4">
          {/* 장소명 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">장소명</label>
            <input 
              type="text" 
              placeholder="장소명을 입력하세요"
              value={formData.placeName}
              onChange={(e) => handleInputChange('placeName', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              required
            />
          </div>

          {/* 우편번호 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">우편번호</label>
            <div className="flex space-x-1">
              <input 
                type="text" 
                placeholder="12345"
                value={formData.postalCode}
                onChange={(e) => handleInputChange('postalCode', e.target.value)}
                className="w-16 px-2 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
                required
              />
              <span className="flex items-center text-gray-500 text-sm">-</span>
              <input 
                type="text" 
                placeholder="67890"
                className="w-16 px-2 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              />
              <button 
                type="button"
                className="px-2 py-2 bg-gray-200 hover:bg-gray-300 text-gray-700 rounded-lg font-medium transition-all duration-200 whitespace-nowrap text-xs"
              >
                검색
              </button>
            </div>
          </div>

          {/* 주소 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">주소</label>
            <div className="relative">
              <input 
                type="text" 
                placeholder="주소"
                value={formData.address}
                onChange={(e) => handleInputChange('address', e.target.value)}
                className="w-full px-3 py-2 pr-10 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
                required
              />
              <div className="absolute right-2 top-1/2 transform -translate-y-1/2">
                <svg className="w-4 h-4 text-gray-500" fill="currentColor" viewBox="0 0 20 20">
                  <path fillRule="evenodd" d="M10 2C6.686 2 4 4.686 4 8c0 5.25 6 10 6 10s6-4.75 6-10c0-3.314-2.686-6-6-6zm0 8c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clipRule="evenodd" />
                </svg>
              </div>
            </div>
          </div>

          {/* 상세주소 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">상세주소</label>
            <input 
              type="text" 
              placeholder="상세주소"
              value={formData.detailAddress}
              onChange={(e) => handleInputChange('detailAddress', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
            />
          </div>

          {/* 위도/경도 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">위도/경도</label>
            <input 
              type="text" 
              placeholder="위도,경도 (예: 37.5665, 126.9780)"
              value={formData.coordinates}
              onChange={(e) => handleInputChange('coordinates', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              required
            />
          </div>

          {/* 관리주체 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">관리주체</label>
            <input 
              type="text" 
              placeholder="관리주체를 입력하세요"
              value={formData.manager}
              onChange={(e) => handleInputChange('manager', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              required
            />
          </div>

          {/* 연락처 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">연락처</label>
            <input 
              type="tel" 
              placeholder="연락처를 입력하세요"
              value={formData.contact}
              onChange={(e) => handleInputChange('contact', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              required
            />
          </div>

          {/* 개방시간 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">개방시간</label>
            <input 
              type="text" 
              placeholder="개방시간을 입력하세요 (예: 09:00-18:00)"
              value={formData.openHours}
              onChange={(e) => handleInputChange('openHours', e.target.value)}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
              required
            />
          </div>

          {/* 이미지 업로드 */}
          <div>
            <label className="block text-xs font-medium text-gray-700 mb-1">이미지</label>
            <input 
              type="file" 
              accept="image/*"
              onChange={(e) => handleInputChange('image', e.target.files[0])}
              className="w-full px-3 py-2 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-1 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 text-sm"
            />
          </div>

          {/* 제출 버튼 */}
          <button 
            type="submit"
            className="w-full bg-gray-800 hover:bg-gray-900 text-white py-2 px-3 rounded-lg font-medium transition-all duration-200 hover:shadow-lg active:scale-95 text-sm"
          >
            제출
          </button>
        </form>
      </div>
    </div>
  )
}

export default PlaceRequestForm 
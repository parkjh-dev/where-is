import React, { useState } from 'react'
import CancelButton from './CancelButton'

const PlaceReportForm = ({ onClose }) => {
  const [formData, setFormData] = useState({
    category: '',
    content: ''
  })

  const handleInputChange = (field, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    console.log('신고 데이터:', formData)
    // 신고 제출 후 모달 닫기
    if (onClose) {
      onClose()
    }
  }

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
        <div className="bg-white rounded-xl p-6 w-full max-w-md mx-4 relative">
        <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-bold text-gray-800">장소 신고</h2>
            <CancelButton onClick={onClose} />
        </div>
        <form onSubmit={handleSubmit} className="space-y-4">
        <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">신고 카테고리</label>
            <select 
            value={formData.category}
            onChange={(e) => handleInputChange('category', e.target.value)}
            className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900"
            required
            >
            <option value="">카테고리를 선택하세요</option>
            <option value="incorrect_info">잘못된 정보</option>
            <option value="closed_place">폐업한 장소</option>
            <option value="inappropriate_content">부적절한 내용</option>
            <option value="spam">스팸</option>
            <option value="other">기타</option>
            </select>
        </div>

        <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">신고 내용</label>
            <textarea 
            placeholder="신고 사유를 자세히 입력해주세요"
            value={formData.content}
            onChange={(e) => handleInputChange('content', e.target.value)}
            className="w-full px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-gray-500 focus:border-gray-500 transition-all duration-200 text-gray-900 resize-none"
            rows={4}
            required
            />
        </div>

        <div className="flex space-x-3">
            <button 
            type="button"
            onClick={onClose}
            className="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-700 py-3 px-4 rounded-xl font-semibold transition-all duration-200"
            >
            취소
            </button>
            <button 
            type="submit"
            className="flex-1 bg-gray-800 hover:bg-gray-900 text-white py-3 px-4 rounded-xl font-semibold transition-all duration-200 hover:shadow-lg active:scale-95"
            >
            제출
            </button>
        </div>
        </form>
        </div>
    </div>
  )
}

export default PlaceReportForm 
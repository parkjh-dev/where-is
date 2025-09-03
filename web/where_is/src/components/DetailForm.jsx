import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import DetailSummaryForm from './DetailSummaryForm'

const DetailForm = ({ 

}) => {
  const [selectedRating, setSelectedRating] = useState(0)
  const handleSubmit = (e) => {
    e.preventDefault()
    if (onEmailLogin) {
      onEmailLogin()
    }
  }

  return (
    <div className="min-h-full bg-gray-50">
      {/* 상단 바 */}
      <div className="bg-white border-b border-gray-200 px-4 py-3">
        <img 
          src="/pictogram/back.png" 
          alt="뒤로가기" 
          className="w-6 h-6 cursor-pointer hover:opacity-70 transition-opacity duration-200"
          onClick={() => window.history.back()}
        />
      </div>

      {/* 이미지 섹션 */}
      <div className="w-full h-64 bg-gray-200 flex items-center justify-center overflow-hidden">
        <img 
          src="/example_img.JPG" 
          alt="시설 이미지" 
          className="w-full h-full object-cover"
        />
      </div>
    <DetailSummaryForm />
      {/* 평점 입력 섹션 */}
      <div className="bg-white p-4 border-b border-gray-200">
        {/* 리뷰 헤더 */}
        <div className="mb-3">
          <h3 className="text-lg font-semibold text-gray-800">리뷰 10개</h3>
        </div>
        
        {/* 별점 선택 */}
        <div className="mb-3">
          <div className="flex items-center space-x-0.5">
            {[1, 2, 3, 4, 5].map((star) => (
              <button
                key={star}
                className="text-lg text-gray-300 hover:text-yellow-400 transition-colors duration-200 bg-transparent border-none p-0"
                onClick={() => setSelectedRating(star)}
              >
                {star <= selectedRating ? <span className="text-yellow-400">★</span> : '☆'}
              </button>
            ))}
          </div>
        </div>
        
        <div className="flex items-center space-x-3">
            <input 
            type="email" 
            placeholder="리뷰를 작성해주세요"
            className="flex-1 px-4 py-3 bg-white border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-cyan-500 focus:border-cyan-500 transition-all duration-200 text-gray-800"
            required
          />
          <button className="bg-gray-600 text-white px-4 py-3 rounded-xl text-sm whitespace-nowrap">
            입력
          </button>
        </div>
      </div>

      {/* 평점(리뷰) 목록 섹션 */}
      <div className="bg-white p-4">
        {/* 정렬 옵션 */}
        <div className="flex justify-start mb-4">
          <select className="px-3 py-1 border-none rounded-md text-sm bg-white text-gray-700 focus:outline-none">
            <option value="latest">최신순</option>
            <option value="rating">별점순</option>
          </select>
        </div>
        
        <div className="border border-gray-200 p-4 rounded-lg mb-3">
          <div className="flex items-start space-x-3">
            {/* 프로필 이미지 */}
            <img 
              src="/exam_profile_1.png" 
              alt="프로필" 
              className="w-10 h-10 rounded-full object-cover flex-shrink-0"
            />
            
            {/* 리뷰 내용 */}
            <div className="flex-1">
              {/* 사용자명, 별점, 작성일 */}
              <div className="flex items-center justify-between mb-2">
                <div className="flex items-center space-x-2">
                  <span className="font-medium text-gray-800">parkjh</span>
                  <div className="flex items-center">
                    <span className="text-yellow-400 text-sm">★★★★★</span>
                  </div>
                </div>
                <span className="text-xs text-gray-500">2025-01-01 00:00</span>
              </div>
              
              {/* 리뷰 텍스트 */}
              <p className="text-gray-700">깨끗하고 항상 개방되어 있어서 좋아요.</p>
            </div>
          </div>
        </div>

        <div className="border border-gray-200 p-4 rounded-lg">
          <div className="flex items-start space-x-3">
            {/* 프로필 이미지 */}
            <img 
              src="/exam_profile_2.png" 
              alt="프로필" 
              className="w-10 h-10 rounded-full object-cover flex-shrink-0"
            />
            
            {/* 리뷰 내용 */}
            <div className="flex-1">
              {/* 사용자명, 별점, 작성일 */}
              <div className="flex items-center justify-between mb-2">
                <div className="flex items-center space-x-2">
                  <span className="font-medium text-gray-800">pjh6444</span>
                  <div className="flex items-center">
                    <span className="text-yellow-400 text-sm">★★★★☆</span>
                  </div>
                </div>
                <span className="text-xs text-gray-500">2025-01-01 00:00</span>
              </div>
              
              {/* 리뷰 텍스트 */}
              <p className="text-gray-700">좋아요.</p>
            </div>
          </div>
        </div>

        
      </div>
    </div>
  )
}

export default DetailForm 
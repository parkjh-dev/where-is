import React, { useState } from 'react'
import DetailSummaryForm from './DetailSummaryForm'
import BackButton from './BackButton';
import Review from './Review'
import FacilityImage from './FacilityImage'

const DetailForm = ({ facilityData, reviewDataList}) => {
  const [selectedRating, setSelectedRating] = useState(0)
  const s3BaseURL = import.meta.env.VITE_S3_BASE_URL;

  return (
    <div className="min-h-full bg-gray-50">
      {/* 상단 바 */}
      <div className="bg-white border-b border-gray-200 px-4 py-3">
        <BackButton />
      </div>

    {/* 이미지 섹션 */}
    <FacilityImage imagePath={`${s3BaseURL}/${facilityData.facilityImage}`}/>

    {/* 상세 정보 섹션 */}
    <DetailSummaryForm 
      facilityData={facilityData}
    />

      {/* 평점 입력 섹션 */}
      <div className="bg-white p-4 border-b border-gray-200">
        {/* 리뷰 헤더 */}
        <div className="mb-3">
          <h3 className="text-lg font-semibold text-gray-800">리뷰 {facilityData.reviewCount}개</h3>
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
        {reviewDataList.map((reviewData) => (
          <Review key={reviewData.id} reviewData={reviewData} />
        ))}
      </div>
    </div>
  )
}

export default DetailForm 
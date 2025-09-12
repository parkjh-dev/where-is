import React from 'react';
import StarRating from './StarRating';

function Review({ reviewData }) {
    
  return (
    <div className="border border-gray-200 p-4 rounded-lg mb-3">
        <div className="flex items-start space-x-3">
        {/* 프로필 이미지 */}
        <img 
            src={reviewData.profileImage} 
            alt="profile" 
            className="w-10 h-10 rounded-full object-cover flex-shrink-0"
        />
        
        {/* 리뷰 내용 */}
        <div className="flex-1">
            {/* 사용자명, 별점, 작성일 */}
            <div className="flex items-center justify-between mb-2">
            <div className="flex items-center space-x-2">
                <span className="font-medium text-gray-800">{reviewData.nickName}</span>
                <div className="flex items-center">
                <StarRating rating={reviewData.starRating} size="text-lg" />
                </div>
            </div>
            <span className="text-xs text-gray-500">{reviewData.addDate}</span>
            </div>
            
            {/* 리뷰 텍스트 */}
            <p className="text-gray-700">{reviewData.comment}</p>
        </div>
        </div>
    </div>
  );
}

export default Review;





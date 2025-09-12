import React from 'react';

const StarRating = ({ rating = 0, size = 'text-lg' }) => {
  const fullStars = Math.floor(rating);
  const hasHalfStar = rating % 1 >= 0.5;
  const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

  return (
    <div className="flex items-center">
      {/* 채워진 별 */}
      {Array.from({ length: fullStars }, (_, index) => (
        <span key={`full-${index}`} className={`text-yellow-400 ${size}`}>
          ★
        </span>
      ))}
      
      {/* 반개 별 */}
      {hasHalfStar && (
        <span className={`text-yellow-400 ${size} relative`}>
          <span className="absolute inset-0 overflow-hidden" style={{ width: '50%' }}>
            ★
          </span>
          <span className="text-gray-300">★</span>
        </span>
      )}
      
      {/* 빈 별 */}
      {Array.from({ length: emptyStars }, (_, index) => (
        <span key={`empty-${index}`} className={`text-gray-300 ${size}`}>
          ★
        </span>
      ))}
    </div>
  );
};

export default StarRating;

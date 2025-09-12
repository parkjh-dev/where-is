import React from 'react'
import SirenButton from './SirenButton'
import StarRating from './StarRating'

const DetailSummaryForm = ({ onOpenReportModal, facilityData }) => {
  return (
    <div className="bg-white p-4 border-b border-gray-200">
    <div className="mb-4">
      <div className="flex items-center justify-between mb-2">
        <div className="flex items-center space-x-2">
          <h1 className="text-xl font-bold text-gray-800">{facilityData.subject}</h1>
          <span className="text-white px-2 py-1 rounded-full text-xs font-medium" style={{ backgroundColor: '#A1D6CB' }}>{facilityData.type}</span>
        </div>
        <SirenButton onClick={onOpenReportModal} />
    </div>
      <div className="flex items-center space-x-2">
        <div className="flex items-center">
          <StarRating rating={facilityData.starRating} size="text-lg" />
          <span className="text-gray-600 ml-2">{facilityData.starRating}</span>
        </div>
        <span className="text-gray-400 text-sm">(리뷰 {facilityData.reviewCount}개)</span>
      </div>
    </div>

    {/* 상세 정보 */}
    <div className="space-y-3">
      <div className="flex items-start space-x-3">
        <div className="w-5 h-5 flex-shrink-0 mt-0.5">
          <svg className="w-5 h-5 text-gray-400" fill="currentColor" viewBox="0 0 20 20">
            <path fillRule="evenodd" d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z" clipRule="evenodd" />
          </svg>
        </div>
        <span className="text-gray-700">{facilityData.roadnm_addr}</span>
      </div>
      
      <div className="flex items-start space-x-3">
        <div className="w-5 h-5 flex-shrink-0 mt-0.5">
          <svg className="w-5 h-5 text-gray-400" fill="currentColor" viewBox="0 0 20 20">
            <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clipRule="evenodd" />
          </svg>
        </div>
        <span className="text-gray-700">{facilityData.operatingHours}</span>
      </div>
      
      <div className="flex items-start space-x-3">
        <div className="w-5 h-5 flex-shrink-0 mt-0.5">
          <svg className="w-5 h-5 text-gray-400" fill="currentColor" viewBox="0 0 20 20">
            <path d="M2 3a1 1 0 011-1h2.153a1 1 0 01.986.836l.74 4.435a1 1 0 01-.54 1.06l-1.548.773a11.037 11.037 0 006.105 6.105l.774-1.548a1 1 0 011.059-.54l4.435.74a1 1 0 01.836.986V17a1 1 0 01-1 1h-2C7.82 18 2 12.18 2 5V3z" />
          </svg>
        </div>
        <span className="text-gray-700">{facilityData.agencyContact}</span>
      </div>
      
      <div className="flex items-start space-x-3">
        <div className="w-5 h-5 flex-shrink-0 mt-0.5">
          <svg className="w-5 h-5 text-gray-400" fill="currentColor" viewBox="0 0 20 20">
            <path fillRule="evenodd" d="M4 4a2 2 0 00-2 2v8a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2H4zm0 2h12v8H4V6zm2 2a1 1 0 011-1h6a1 1 0 110 2H7a1 1 0 01-1-1zm0 3a1 1 0 011-1h6a1 1 0 110 2H7a1 1 0 01-1-1z" clipRule="evenodd" />
          </svg>
        </div>
        <span className="text-gray-700">{facilityData.managingAgency}</span>
      </div>
    </div>
  </div>
  );
}

export default DetailSummaryForm 
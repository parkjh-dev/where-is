import React, { useEffect, useState } from 'react'
import DetailSummary from './DetailSummary'
import PlaceReportForm from './PlaceReportForm'
import SearchAndFilter from './SearchAndFilter'
import useMapMarkers from '../hooks/useMapMarkers.jsx'
import useNaverMap from '../hooks/useNaverMap.jsx'
import useMapPosition from '../hooks/useMapPosition.jsx'

const markerData = 
  [{ 
    id: 1,
    name: "수원 월드컵 경기장 중앙광장",
    type: "trash",
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: "IMG_4517.JPG",
    latitude: 37.288212,
    longitude: 127.034632,
  },
  { 
    id: 2,
    name: "수원 월드컵 경기장 북편",
    type: "smoking",
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: 'IMG_4511.JPG',
    latitude: 37.287873,
    longitude: 127.036329,
  },
  { 
    id: 3,
    name: "수원 월드컵 경기장 남편",
    type: "smoking",
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: "IMG_4512.JPG",
    latitude: 37.285768,
    longitude: 127.035692,
  },
  { 
    id: 4,
    name: "수원월드컵경기장 축구공 함성",
    type: "toilet",
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: 'IMG_4510.JPG',
    latitude: 37.288686,
    longitude: 127.034892,
    
  },
  { 
    id: 5,
    name: '수원월드컵경기장 축구공 감동',
    type: 'toilet',
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: "IMG_4516.JPG",
    latitude: 37.287338,
    longitude: 127.034179
  },
  { 
    id: 6,
    name: "수원월드컵경기장 축구공 하나",
    type: "toilet",
    roadnmAddr: '경기 수원시 팔달구 우만동 222',
    operatingHours: '00:00 ~ 23:59',
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5,
    facilityImage: 'IMG_4513.JPG',
    latitude: 37.284889,
    longitude: 127.036677,
  }
];

const MainForm = ({ 
  className = ""
}) => {
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false)
  const [isReportModalOpen, setIsReportModalOpen] = useState(false)
  const [selectedFacility, setSelectedFacility] = useState(null)
  const { createMarkers } = useMapMarkers();
  const { savePosition, restorePosition, setupAutoRestore } = useMapPosition();
  
  // 지도 초기화 후 마커 생성 콜백
  const handleMapReady = (mapInstance) => {
    createMarkers(mapInstance, markerData, handleMarkerClick);
  };

  const { mapRef, mapInstanceRef } = useNaverMap(handleMapReady);
  
  const handleMarkerClick = (facilityData) => {
    savePosition(mapInstanceRef.current);
    setSelectedFacility(facilityData)
    setIsDetailModalOpen(true)
  }

  const handleCloseModal = () => {
    setIsDetailModalOpen(false)
    setSelectedFacility(null)
  }

  const handleOpenReportModal = () => {
    setIsReportModalOpen(true)
    setIsDetailModalOpen(false)
  }

  const handleCloseReportModal = () => {
    setIsReportModalOpen(false)
  }

  const restoreMapPosition = () => {
    restorePosition(mapInstanceRef.current);
  }

  // 저장된 지도 위치 복원
  useEffect(() => {
    setupAutoRestore(mapInstanceRef);
  }, [])

  return (
    <div className={`h-full ${className}`}>
      {/* 지도 컨테이너 */}
      <div className="relative w-full h-full overflow-hidden">
        {/* 지도 */}
        <div 
          ref={mapRef} 
          className="w-full h-full"
        />
        {/* 검색 및 필터 */}
        <SearchAndFilter />
      </div>
      
      {/* DetailSummary 모달 */}
      <DetailSummary 
        isOpen={isDetailModalOpen}
        onClose={handleCloseModal}
        facilityData={selectedFacility}
        onRestoreMapPosition={restoreMapPosition}
        onOpenReportModal={handleOpenReportModal}
      />

      {/* 신고 모달 */}
      {isReportModalOpen && (       
        <PlaceReportForm onClose={handleCloseReportModal} />
      )}
    </div>
  )
}

export default MainForm 
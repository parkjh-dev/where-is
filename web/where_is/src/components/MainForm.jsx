import React, { useEffect, useState } from 'react'
import DetailSummary from './DetailSummary'
import PlaceReportForm from './PlaceReportForm'
import SearchAndFilter from './SearchAndFilter'
import useMapMarkers from '../hooks/useMapMarkers.jsx'
import useNaverMap from '../hooks/useNaverMap.jsx'
import useMapPosition from '../hooks/useMapPosition.jsx'
import { getDatas } from '../api/BaseAPI';

const PAGE_SIZE = parseInt(import.meta.env.VITE_PAGE_SIZE);

const MainForm = ({ 
  className = ""
}) => {
  const { mapRef, mapInstanceRef } = useNaverMap();
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false)
  const [isReportModalOpen, setIsReportModalOpen] = useState(false)
  const [selectedFacility, setSelectedFacility] = useState(null)
  const { createMarkers } = useMapMarkers();
  const { savePosition, restorePosition, setupAutoRestore } = useMapPosition();
  const [toiletItems, setToiletItems] = useState([]);
  const [smokingItems, setSmokingItems] = useState([]);
  const [trashItems, setTrashItems] = useState([]);

  // 데이터 타입 매핑
  const typeMapper = (type, data) => {
    if (!Array.isArray(data)) return [];
    return data.map((item) => ({ ...item, type:type }));
  }

  const fetchData = (url, type, query) => {
    getDatas(url, query)
      .then(data => {
        console.log(data.data?.content);
        switch(type) {
          case "toilet":
            setToiletItems(typeMapper(type, data.data?.content) || []);
            break;
          case "smoking":
            setSmokingItems(typeMapper(type, data.data?.content) || []);
            break;
          case "trash":
            setTrashItems(typeMapper(type, data.data?.content) || []);
            break;
        }
        //setTotal(data.data?.total || []);
        //setTotalPages(data.data?.pages || 0); 
      })
      .catch(error => console.error(error));
  };

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

  // 데이터 세팅 이후 마커 생성
  useEffect(() => {
    if (!mapInstanceRef.current) return;
    if (!Array.isArray(toiletItems) || toiletItems.length === 0) return;
    createMarkers(mapInstanceRef.current, toiletItems, handleMarkerClick);
  }, [toiletItems]);

  useEffect(() => {
    if (!mapInstanceRef.current) return;
    if (!Array.isArray(smokingItems) || smokingItems.length === 0) return;
    createMarkers(mapInstanceRef.current, smokingItems, handleMarkerClick);
  }, [smokingItems]);

  useEffect(() => {
    if (!mapInstanceRef.current) return;
    if (!Array.isArray(trashItems) || trashItems.length === 0) return;
    createMarkers(mapInstanceRef.current, trashItems, handleMarkerClick);
  }, [trashItems]);

  useEffect(() => {
    setupAutoRestore(mapInstanceRef); // 저장된 지도 위치 복원
    // 데이터 조회
    const query = {
      page: 0,
      size: PAGE_SIZE,
      order: "desc",
      sort: "name"
    }
    fetchData("toilets", "toilet", query);
    fetchData("trash-can", "trash", query);
    fetchData("smoking-area", "smoking", query);
    console.log(typeMapper("toilet", toiletItems));
  }, []);

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
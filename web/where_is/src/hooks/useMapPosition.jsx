import { useState, useEffect, useCallback } from 'react';

const useMapPosition = () => {
  const [savedMapCenter, setSavedMapCenter] = useState(null);
  const [savedMapZoom, setSavedMapZoom] = useState(null);

  // 지도 위치 저장
  const savePosition = useCallback((mapInstance) => {
    if (!mapInstance) return;

    const center = mapInstance.getCenter();
    const zoom = mapInstance.getZoom();
    
    const mapData = {
      lat: center.lat(),
      lng: center.lng(),
      zoom: zoom
    };

    // 상태에 저장
    setSavedMapCenter({
      lat: center.lat(),
      lng: center.lng()
    });
    setSavedMapZoom(zoom);
    
    // localStorage에도 저장
    localStorage.setItem('savedMapPosition', JSON.stringify(mapData));
    console.log('지도 위치 저장:', mapData);
  }, []);

  // 지도 위치 복원
  const restorePosition = useCallback((mapInstance) => {
    if (!mapInstance || !savedMapCenter || !savedMapZoom) return;

    const center = new window.naver.maps.LatLng(savedMapCenter.lat, savedMapCenter.lng);
    mapInstance.setCenter(center);
    mapInstance.setZoom(savedMapZoom);
  }, [savedMapCenter, savedMapZoom]);

  // localStorage에서 위치 복원
  const restoreFromStorage = useCallback((mapInstance) => {
    const savedPosition = localStorage.getItem('savedMapPosition');
    if (!savedPosition || !mapInstance) return false;

    try {
      const mapData = JSON.parse(savedPosition);
      const center = new window.naver.maps.LatLng(mapData.lat, mapData.lng);
      mapInstance.setCenter(center);
      mapInstance.setZoom(mapData.zoom);
      
      // 복원 후 localStorage에서 제거
      localStorage.removeItem('savedMapPosition');
      console.log('지도 위치 복원 완료:', mapData);
      return true;
    } catch (error) {
      console.error('지도 위치 복원 실패:', error);
      return false;
    }
  }, []);

  // 지도 초기화 후 자동 복원 (useEffect 대신 함수로 제공)
  const setupAutoRestore = useCallback((mapInstanceRef) => {
    const attemptRestore = () => {
      if (mapInstanceRef.current) {
        restoreFromStorage(mapInstanceRef.current);
        return true;
      }
      return false;
    };

    // 지도가 이미 초기화된 경우 즉시 복원
    if (attemptRestore()) {
      return;
    }

    // 지도 초기화를 기다림
    const checkMap = setInterval(() => {
      if (attemptRestore()) {
        clearInterval(checkMap);
      }
    }, 100);
    
    // 5초 후 타임아웃
    setTimeout(() => {
      clearInterval(checkMap);
    }, 5000);
  }, [restoreFromStorage]);

  return {
    savedMapCenter,
    savedMapZoom,
    savePosition,
    restorePosition,
    restoreFromStorage,
    setupAutoRestore
  };
};

export default useMapPosition;

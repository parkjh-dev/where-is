import { useEffect, useRef } from 'react';
import { renderToString } from 'react-dom/server';
import MyLocationButton from '../components/MyLocationButton';

const useNaverMap = () => {
  const mapRef = useRef(null);
  const mapInstanceRef = useRef(null);
  const naverMapBaseUrl = import.meta.env.VITE_NAVER_MAP_BASE_URL;

  const initializeMap = () => {
    if (!mapRef.current || mapInstanceRef.current) return;

    const position = new window.naver.maps.LatLng(37.284889, 127.034179);

    const mapOptions = {
      center: position,
      zoom: 19,
      // 네이버 지도 컨트롤 옵션
      zoomControl: true,
      zoomControlOptions: {
        style: window.naver.maps.ZoomControlStyle.SMALL,
        position: window.naver.maps.Position.RIGHT_CENTER
      },
      mapDataControl: true,     // 지도 데이터 컨트롤 표시
      mapDataControlOptions: {
        position: window.naver.maps.Position.TOP_RIGHT
      },
      scaleControl: true,       // 스케일 컨트롤 표시
      scaleControlOptions: {
        position: window.naver.maps.Position.BOTTOM_LEFT
      },
      logoControl: false,        // 로고 컨트롤 표시
      logoControlOptions: {
        position: window.naver.maps.Position.BOTTOM_RIGHT
      },
    };

    mapInstanceRef.current = new window.naver.maps.Map(mapRef.current, mapOptions);

    // 지도 로드 완료 후 추가 설정
    window.naver.maps.Event.once(mapInstanceRef.current, 'init', () => {
      console.log('지도 초기화 완료');
      
      // 지도 초기화 완료 후 저장된 위치 복원 시도
      const savedPosition = localStorage.getItem('savedMapPosition');
      if (savedPosition) {
        try {
          const mapData = JSON.parse(savedPosition);
          const center = new window.naver.maps.LatLng(mapData.lat, mapData.lng);
          mapInstanceRef.current.setCenter(center);
          mapInstanceRef.current.setZoom(mapData.zoom);
          localStorage.removeItem('savedMapPosition');
          console.log('지도 초기화 후 위치 복원 완료:', mapData);
        } catch (error) {
          console.error('지도 초기화 후 위치 복원 실패:', error);
        }
      }
      
      // 현재 위치 버튼 추가
      setupCurrentLocationButton();
      
    });
  };

  const setupCurrentLocationButton = () => {
    // 현재 위치 버튼 HTML (네이버 지도 스타일)
    const locationBtnHtml = renderToString(<MyLocationButton />);
    
    // 현재 위치 버튼 추가
    const locationButton = new window.naver.maps.CustomControl(locationBtnHtml, {
      position: window.naver.maps.Position.RIGHT_BOTTOM
    });
    
    locationButton.setMap(mapInstanceRef.current);
    
    // 현재 위치 버튼 클릭 이벤트
    window.naver.maps.Event.addDOMListener(locationButton.getElement(), 'click', (e) => {
      e.preventDefault();
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          const lat = position.coords.latitude;
          const lng = position.coords.longitude;
          const currentPosition = new window.naver.maps.LatLng(lat, lng);
          
          mapInstanceRef.current.setCenter(currentPosition);
          mapInstanceRef.current.setZoom(17);
          
          // 현재 위치 마커 추가
          new window.naver.maps.Marker({
            position: currentPosition,
            map: mapInstanceRef.current,
            icon: {
              content: '<div style="background-color: #FF4444; width: 20px; height: 20px; border-radius: 50%; border: 3px solid white; box-shadow: 0 2px 4px rgba(0,0,0,0.3);"></div>',
              size: new window.naver.maps.Size(20, 20),
              anchor: new window.naver.maps.Point(10, 10)
            }
          });
        });
      } else {
        alert('현재 위치를 가져올 수 없습니다.');
      }
    });
  };

  useEffect(() => {
    // 네이버 지도 API가 로드되었는지 확인
    if (window.naver && window.naver.maps) {
      initializeMap();
    } else {
      // API가 로드되지 않았다면 로드 후 초기화
      const script = document.createElement('script');
      script.src = naverMapBaseUrl;
      script.onload = initializeMap;
      script.onerror = () => {
        console.error('네이버 지도 API 로드 실패');
      };
      document.head.appendChild(script);
    }
  }, [naverMapBaseUrl]);

  return {
    mapRef,
    mapInstanceRef
  };
};

export default useNaverMap;

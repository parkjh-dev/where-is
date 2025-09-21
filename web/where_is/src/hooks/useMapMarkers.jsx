import { useCallback } from 'react';
import { renderToString } from 'react-dom/server';
import Marker from '../components/Marker';

const useMapMarkers = () => {
  const createMarkers = useCallback((mapInstance, markerData, onMarkerClick) => {
    
    if (!mapInstance || !markerData) {
      return [];
    }

    const customMarkers = markerData.map((data, index) => {
      const marker = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(data.latitude, data.longitude),
        map: mapInstance,
        icon: {
          content: renderToString(<Marker type={data.type} />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      });

      // 클릭 이벤트 추가
      if (onMarkerClick && data.id) {
        window.naver.maps.Event.addListener(marker, 'click', () => {
          onMarkerClick(data);
        });
      }

      return marker;
    });

    return customMarkers;
  }, []);

  return { createMarkers };
};

export default useMapMarkers;

import React, { useEffect, useRef, useState } from 'react'
import { renderToString } from 'react-dom/server'
import DetailSummary from './DetailSummary'
import PlaceReportForm from './PlaceReportForm'
import MyLocationButton from './MyLocationButton'
import Marker from './Marker'
import SearchAndFilter from './SearchAndFilter'

const MainForm = ({ 
  className = ""
}) => {
  const mapRef = useRef(null)
  const mapInstanceRef = useRef(null)
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false)
  const [isReportModalOpen, setIsReportModalOpen] = useState(false)
  const [selectedFacility, setSelectedFacility] = useState(null)
  const [savedMapCenter, setSavedMapCenter] = useState(null)
  const [savedMapZoom, setSavedMapZoom] = useState(null)


  const handleMarkerClick = (facilityData) => {
    // 현재 지도 위치와 줌 레벨 저장
    if (mapInstanceRef.current) {
      const center = mapInstanceRef.current.getCenter()
      const zoom = mapInstanceRef.current.getZoom()
      const mapData = {
        lat: center.lat(),
        lng: center.lng(),
        zoom: zoom
      }
      setSavedMapCenter({
        lat: center.lat(),
        lng: center.lng()
      })
      setSavedMapZoom(zoom)
      
      // localStorage에도 저장
      localStorage.setItem('savedMapPosition', JSON.stringify(mapData))
      console.log('지도 위치 저장:', mapData)
    }
    
    setSelectedFacility(facilityData)
    setIsDetailModalOpen(true)
  }

  const handleCloseModal = () => {
    setIsDetailModalOpen(false)
    setSelectedFacility(null)
  }

  const handleOpenReportModal = () => {
    setIsReportModalOpen(true)
    setIsDetailModalOpen(false) // DetailSummary 모달 닫기
  }

  const handleCloseReportModal = () => {
    setIsReportModalOpen(false)
  }

  const restoreMapPosition = () => {
    if (mapInstanceRef.current && savedMapCenter && savedMapZoom) {
      const center = new window.naver.maps.LatLng(savedMapCenter.lat, savedMapCenter.lng)
      mapInstanceRef.current.setCenter(center)
      mapInstanceRef.current.setZoom(savedMapZoom)
    }
  }

  useEffect(() => {
    // 네이버 지도 API가 로드되었는지 확인
    if (window.naver && window.naver.maps) {
      initializeMap()
    } else {
      // API가 로드되지 않았다면 로드 후 초기화
      const script = document.createElement('script')
      script.src = 'https://oapi.map.naver.com/openapi/v3/maps.js?ncpKeyId=4fl6frpu13'
      script.onload = initializeMap
      script.onerror = () => {
        console.error('네이버 지도 API 로드 실패')
      }
      document.head.appendChild(script)
    }
  }, [])

  // 저장된 지도 위치 복원
  useEffect(() => {
    const restorePosition = () => {
      const savedPosition = localStorage.getItem('savedMapPosition')
      if (savedPosition && mapInstanceRef.current) {
        try {
          const mapData = JSON.parse(savedPosition)
          const center = new window.naver.maps.LatLng(mapData.lat, mapData.lng)
          mapInstanceRef.current.setCenter(center)
          mapInstanceRef.current.setZoom(mapData.zoom)
          
          // 복원 후 localStorage에서 제거
          localStorage.removeItem('savedMapPosition')
          console.log('지도 위치 복원 완료:', mapData)
        } catch (error) {
          console.error('지도 위치 복원 실패:', error)
        }
      }
    }

    // 지도가 초기화된 후 복원 시도
    if (mapInstanceRef.current) {
      restorePosition()
    } else {
      // 지도 초기화를 기다림
      const checkMap = setInterval(() => {
        if (mapInstanceRef.current) {
          restorePosition()
          clearInterval(checkMap)
        }
      }, 100)
      
      // 5초 후 타임아웃
      setTimeout(() => {
        clearInterval(checkMap)
      }, 5000)
    }
  }, [])

  const initializeMap = () => {
    if (!mapRef.current || mapInstanceRef.current) return

    const position = new window.naver.maps.LatLng(37.3849483, 127.1229117)

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
    }

    mapInstanceRef.current = new window.naver.maps.Map(mapRef.current, mapOptions)

    // 지도 로드 완료 후 마커 추가
    window.naver.maps.Event.once(mapInstanceRef.current, 'init', () => {
      console.log('지도 초기화 완료')
      
      // 지도 초기화 완료 후 저장된 위치 복원 시도
      const savedPosition = localStorage.getItem('savedMapPosition')
      if (savedPosition) {
        try {
          const mapData = JSON.parse(savedPosition)
          const center = new window.naver.maps.LatLng(mapData.lat, mapData.lng)
          mapInstanceRef.current.setCenter(center)
          mapInstanceRef.current.setZoom(mapData.zoom)
          localStorage.removeItem('savedMapPosition')
          console.log('지도 초기화 후 위치 복원 완료:', mapData)
        } catch (error) {
          console.error('지도 초기화 후 위치 복원 실패:', error)
        }
      }
      
      // 현재 위치 버튼 HTML (네이버 지도 스타일)
      const locationBtnHtml = renderToString(<MyLocationButton />)
      
      // 현재 위치 버튼 추가
      const locationButton = new window.naver.maps.CustomControl(locationBtnHtml, {
        position: window.naver.maps.Position.RIGHT_BOTTOM
      })
      
      locationButton.setMap(mapInstanceRef.current)
      
      // 현재 위치 버튼 클릭 이벤트
      window.naver.maps.Event.addDOMListener(locationButton.getElement(), 'click', (e) => {
        e.preventDefault()
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            const lat = position.coords.latitude
            const lng = position.coords.longitude
            const currentPosition = new window.naver.maps.LatLng(lat, lng)
            
            mapInstanceRef.current.setCenter(currentPosition)
            mapInstanceRef.current.setZoom(17)
            
            // 현재 위치 마커 추가
            new window.naver.maps.Marker({
              position: currentPosition,
              map: mapInstanceRef.current,
              icon: {
                content: '<div style="background-color: #FF4444; width: 20px; height: 20px; border-radius: 50%; border: 3px solid white; box-shadow: 0 2px 4px rgba(0,0,0,0.3);"></div>',
                size: new window.naver.maps.Size(20, 20),
                anchor: new window.naver.maps.Point(10, 10)
              }
            })
          })
        } else {
          alert('현재 위치를 가져올 수 없습니다.')
        }
      })

      const customMarker = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.288212, 127.034632),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="trash" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })

      const customMarker1 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.3849483 - 0.002, 127.1229117 - 0.002),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="toilet" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })
      
      const customMarker2 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.287873, 127.036329),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="smoking" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })

      const customMarker3 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.285768, 127.035692),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="smoking" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })

      const customMarker4 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.288686, 127.034892),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="toilet" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })


      // 두 번째 마커 추가 (다른 스타일)
      const customMarker5 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.287338, 127.034179),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="smoking" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })


      const customMarker6 = new window.naver.maps.Marker({
        position: new window.naver.maps.LatLng(37.284889, 127.036677),
        map: mapInstanceRef.current,
        icon: {
          content: renderToString(<Marker type="toilet" />),
          size: new window.naver.maps.Size(40, 40),
          anchor: new window.naver.maps.Point(20, 20)
        }
      })

      // 마커 클릭 이벤트 추가
      window.naver.maps.Event.addListener(customMarker5, 'click', () => {
        handleMarkerClick({
          id: 1,
          name: '수원월드컵경기장 축구공 감동',
          type: 'Toilet',
          color: '#A1D6CB',
          address: '경기 수원시 팔달구 우만동 222',
          hours: '00:00 ~ 23:59',
          image: '/example_img.JPG'
        })
      })
    })
  }

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
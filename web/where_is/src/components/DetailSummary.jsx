import React, { useState, useRef, useEffect } from 'react'
import DetailSummaryForm from './DetailSummaryForm'

const DetailSummary = ({ isOpen, onClose, facilityData, onRestoreMapPosition }) => {
  const [isDragging, setIsDragging] = useState(false)
  const [startY, setStartY] = useState(0)
  const [currentY, setCurrentY] = useState(0)
  const [dragDirection, setDragDirection] = useState('') // 'up', 'down', ''
  const modalRef = useRef(null)

  const handleTouchStart = (e) => {
    setIsDragging(true)
    setStartY(e.touches[0].clientY)
    setCurrentY(e.touches[0].clientY)
    setDragDirection('')
  }

  const handleTouchMove = (e) => {
    if (!isDragging) return
    // 드래그 중에는 페이지 스크롤 방지
    e.preventDefault()
    const newY = e.touches[0].clientY
    setCurrentY(newY)
    
    // 드래그 방향 감지
    const deltaY = newY - startY
    if (deltaY > 20) {
      setDragDirection('down')
    } else if (deltaY < -20) {
      setDragDirection('up')
    } else {
      setDragDirection('')
    }
  }

  const handleTouchEnd = () => {
    if (!isDragging) return
    setIsDragging(false)
    
    const deltaY = currentY - startY
    if (deltaY > 100) { // 100px 이상 아래로 드래그하면 닫기
      onClose()
    } else if (deltaY < -100) { // 100px 이상 위로 드래그하면 DetailForm으로 이동
      // 자연스러운 전환을 위해 애니메이션 후 이동
      setTimeout(() => {
        window.location.href = `/smoking-area/${facilityData?.id}`
      }, 200)
    }
    setDragDirection('')
  }

  // 마우스 드래그 지원
  const handleMouseDown = (e) => {
    setIsDragging(true)
    setStartY(e.clientY)
    setCurrentY(e.clientY)
    setDragDirection('')
  }

  useEffect(() => {
    if (!isDragging) {
      return
    }

    const onMouseMove = (e) => {
      const newY = e.clientY
      setCurrentY(newY)
      
      // 드래그 방향 감지
      const deltaY = newY - startY
      if (deltaY > 20) {
        setDragDirection('down')
      } else if (deltaY < -20) {
        setDragDirection('up')
      } else {
        setDragDirection('')
      }
    }
    
    const onMouseUp = () => {
      setIsDragging(false)
      const deltaY = currentY - startY
      if (deltaY > 100) {
        onClose()
      } else if (deltaY < -100) {
        // 자연스러운 전환을 위해 애니메이션 후 이동
        setTimeout(() => {
          window.location.href = `/smoking-area/${facilityData?.id}`
        }, 200)
      }
      setDragDirection('')
    }

    document.addEventListener('mousemove', onMouseMove)
    document.addEventListener('mouseup', onMouseUp)
    
    return () => {
      document.removeEventListener('mousemove', onMouseMove)
      document.removeEventListener('mouseup', onMouseUp)
    }
  }, [isDragging, currentY, startY, onClose])

  if (!isOpen) return null

  return (
    <>
      {/* 배경 오버레이 */}
      <div 
        className="fixed inset-0 bg-black bg-opacity-50 z-[9998]"
        onClick={onClose}
      />
      
      {/* 모달 컨테이너 */}
      <div className="fixed bottom-0 left-0 right-0 z-[9999] transform transition-transform duration-300 ease-out flex justify-center">
        <div 
          ref={modalRef}
          className={`w-1/2 mx-auto max-w-[360px] sm:max-w-[380px] md:max-w-[400px] lg:max-w-[480px] bg-white rounded-t-2xl shadow-2xl max-h-[70vh] overflow-y-auto transition-all duration-200 ease-out ${
            dragDirection === 'up' ? 'shadow-blue-200' : 
            dragDirection === 'down' ? 'shadow-red-200' : ''
          }`}
          style={{
            transform: isDragging ? `translateY(${Math.max(0, currentY - startY)}px)` : 'translateY(0)',
            opacity: dragDirection === 'up' ? 0.8 : 1
          }}
        >
          {/* 드래그 핸들 */}
          <div 
            className="flex flex-col items-center pt-3 pb-2 cursor-grab active:cursor-grabbing select-none"
            onTouchStart={handleTouchStart}
            onTouchMove={handleTouchMove}
            onTouchEnd={handleTouchEnd}
            onMouseDown={handleMouseDown}
          >
            <div className="w-12 h-1 bg-gray-300 rounded-full"></div>
            {dragDirection === 'up' && (
              <div className="text-xs text-blue-500 mt-1 font-medium">
                ↑ 상세보기로 이동
              </div>
            )}
            {dragDirection === 'down' && (
              <div className="text-xs text-red-500 mt-1 font-medium">
                ↓ 닫기
              </div>
            )}
          </div>

          <DetailSummaryForm onRestoreMapPosition={onRestoreMapPosition} />

        </div>
      </div>
    </>
  )
}

export default DetailSummary
import React from 'react'
import { useLocation } from 'react-router-dom'
import DetailForm from '../components/DetailForm'

const Detail = () => {
  const location = useLocation()
  const facilityData = location.state?.facilityData

  const reviewDataList = [
    {
      id: 1,
      profileImage: "/exam_profile_1.png",
      nickName: "parkjh",
      addDate: "2025-01-01 00:00",
      comment: "깨끗하고 항상 개방되어 있어서 좋아요.",
      starRating: 4.5
    },
    {
      id: 2,
      profileImage: "/exam_profile_2.png",
      nickName: "pjh6444",
      addDate: "2025-01-01 00:00",
      comment: "좋아요.",
      starRating: 4.5
    }
  ]

  return (
    <DetailForm facilityData={facilityData} reviewDataList={reviewDataList}/>
  )
}

export default Detail 
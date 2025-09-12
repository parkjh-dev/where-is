import React from 'react'
import DetailForm from '../components/DetailForm'

const Detail = () => {

  const facilityData = {
    subject: "수원월드컵경기장 축구공 감동",
    type: "Toilet",
    roadnm_addr: "경기 수원시 팔달구 우만동 222",
    operatingHours: "00:00 ~ 23:59",
    agencyContact: "031-0000-0000",
    managingAgency: "(재)경기도수원월드컵경기장관리재단 시설운영팀",
    reviewCount: 10,
    starRating: 4.5
  }

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
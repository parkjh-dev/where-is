import React from 'react'
import DetailSummaryForm from '../components/DetailSummaryForm'

const DetailSummary = () => {
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

  return (
    <DetailSummaryForm
      facilityData={facilityData}
    />
  )
}

export default DetailSummary 
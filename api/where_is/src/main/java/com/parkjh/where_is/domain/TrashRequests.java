package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "trash_can_area_requests") // DB 테이블 이름
public class TrashRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    private String name;
    private String longitude; // 경도
    private String latitude; // 위도
    @Column(name = "managing_agency")
    private String managingAgency;
    @Column(name = "agency_contact")
    private String agencyContact;
    @Column(name = "roadnm_addr")
    private String roadnmAddr;
    @Column(name = "lotno_addr")
    private String lotnoAddr;
    @Column(name = "operating_hours")
    private String operatingHours;
    @Column(name = "facility_image")
    private String facilityImage;
    @JsonIgnore
    @Column(name = "mod_dt", updatable = true, insertable = false)
    private LocalDateTime modDt;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    public TrashRequests() {}
    public TrashRequests(
            String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
            String facilityImage,
            LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.managingAgency = managingAgency;
        this.agencyContact = agencyContact;
        this.roadnmAddr = roadnmAddr;
        this.lotnoAddr = lotnoAddr;
        this.operatingHours = operatingHours;
        this.facilityImage = facilityImage;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}

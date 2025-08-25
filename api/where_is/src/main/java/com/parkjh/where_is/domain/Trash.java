package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "trash_can_area")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trash {
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
    @JsonIgnore
    @Column(name = "mod_dt", updatable = true, insertable = false)
    private LocalDateTime modDt;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    // Like와의 관계 (1:N)
//    @OneToMany(mappedBy = "smoking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<SmokingLike> sokingLikes;

    public Trash() {}

    public Trash(
             String name, String longitude,
             String latitude, String managingAgency, String agencyContact,
             String roadnmAddr, String lotnoAddr, String operatingHours,
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
        this.modDt = modDt;
        this.addDt = addDt;
    }
}

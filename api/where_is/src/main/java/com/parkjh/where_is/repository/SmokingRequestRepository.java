package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.SmokingRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmokingRequestRepository extends JpaRepository<SmokingRequests, Long> {

}

package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.TrashRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashRequestRepository extends JpaRepository<TrashRequests, Long> {

}

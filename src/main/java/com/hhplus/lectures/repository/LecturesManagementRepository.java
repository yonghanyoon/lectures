package com.hhplus.lectures.repository;


import com.hhplus.lectures.domain.entity.Lectures.LecturesManagement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturesManagementRepository extends JpaRepository<LecturesManagement, Long> {
    List<LecturesManagement> findAllByLecturesIdAndLecturesDateAfter(Long lecturesId, LocalDateTime LecturesDate);
    Optional<LecturesManagement> findByManagementId(Long managementId);
}

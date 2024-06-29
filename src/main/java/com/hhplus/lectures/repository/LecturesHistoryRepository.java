package com.hhplus.lectures.repository;


import com.hhplus.lectures.common.type.RegistStatus;
import com.hhplus.lectures.repository.entity.LecturesHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturesHistoryRepository extends JpaRepository<LecturesHistory, Long> {
    Optional<LecturesHistory> findByUserIdAndManagementId(Long userId, Long managementId);
    List<LecturesHistory> findAllByUserIdAndStatus(Long userId, RegistStatus status);
}

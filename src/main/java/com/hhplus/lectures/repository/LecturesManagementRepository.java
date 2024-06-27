package com.hhplus.lectures.repository;


import com.hhplus.lectures.domain.entity.Lectures.LecturesManagement;
import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface LecturesManagementRepository extends JpaRepository<LecturesManagement, Long> {
    List<LecturesManagement> findAllByLecturesIdAndLecturesDateAfter(Long lecturesId, LocalDateTime LecturesDate);
    Optional<LecturesManagement> findByManagementId(Long managementId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from LecturesManagement m where m.managementId = :managementId")
    LecturesManagement findByManagementIdWithPessimisticLock(Long managementId);
}

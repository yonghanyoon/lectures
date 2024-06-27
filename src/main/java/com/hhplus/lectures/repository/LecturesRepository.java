package com.hhplus.lectures.repository;

import com.hhplus.lectures.domain.entity.Lectures.Lectures;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LecturesRepository extends JpaRepository<Lectures, Long> {

    List<Lectures> findAll();
    Optional<Lectures> findByLecturesId(Long lecturesId);
}

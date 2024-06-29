package com.hhplus.lectures.repository;

import com.hhplus.lectures.repository.entity.Lectures;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturesRepository extends JpaRepository<Lectures, Long> {

    List<Lectures> findAll();
    Optional<Lectures> findByLecturesId(Long lecturesId);
}

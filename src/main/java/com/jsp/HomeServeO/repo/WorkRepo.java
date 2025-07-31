package com.jsp.HomeServeO.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.entity.Work;

public interface WorkRepo extends JpaRepository<Work, Integer> {
	/**
	 * Retrieves a list of works that are not mapped by vendors or have not been
	 * started yet. Works are considered unmapped if they have a null start date.
	 *
	 * @return List of Work objects that are unmapped or not started yet.
	 */
	@Query("SELECT a FROM Work a WHERE a.startDate IS NULL")
	public List<Work> unmappedWorkList();

	/**
	 * Retrieves a list of works that have a start date but do not have an end date,
	 * indicating they are currently ongoing.
	 *
	 * @return List of Work objects that are currently ongoing.
	 */
	@Query("SELECT w FROM Work w WHERE w.startDate IS NOT NULL AND w.endDate IS NULL")
	public List<Work> ongoingWorks();

	/**
	 * Retrieves a list of works that have both start and end dates, indicating they
	 * have been completed.
	 *
	 * @return List of Work objects that have been completed.
	 */
	@Query("SELECT w FROM Work w WHERE w.startDate IS NOT NULL AND w.endDate IS NOT NULL")
	public List<Work> completedWorks();

}

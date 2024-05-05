package com.xyz.repos.seat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.common.jpa.entities.SeatVO;

public interface ISeatRepo extends JpaRepository<SeatVO, Integer> {

}

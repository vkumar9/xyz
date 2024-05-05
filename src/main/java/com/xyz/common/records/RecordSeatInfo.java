package com.xyz.common.records;
import java.util.List;

import com.xyz.common.dtos.SeatInfoDTO;

 
public record RecordSeatInfo(List<SeatInfoDTO>availableSeats,List<SeatInfoDTO>filledSeats) {

}

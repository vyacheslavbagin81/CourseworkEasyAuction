package ru.skypro.coursework.courseworkeasyauction.service.maping;

import ru.skypro.coursework.courseworkeasyauction.dto.FullLot;
import ru.skypro.coursework.courseworkeasyauction.dto.FullLotProjection;

public class FullLotMap {
    public static FullLot toFullLot(FullLotProjection projection) {
        return FullLot.builder()
                .lotId(projection.getLotId())
                .title(projection.getTitle())
                .description(projection.getDescription())
                .status(projection.getStatus())
                .startPrice(projection.getStartPrice())
                .bidPrice(projection.getBidPrice())
                .currentPrice(projection.getCurrentPrice())
                .lastBidName(projection.getLastBidName())
                .lastBidData(projection.getLastBidData())
                .build();
    }
}

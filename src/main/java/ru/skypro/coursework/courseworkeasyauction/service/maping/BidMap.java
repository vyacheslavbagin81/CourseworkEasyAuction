package ru.skypro.coursework.courseworkeasyauction.service.maping;


import ru.skypro.coursework.courseworkeasyauction.dto.Bid;
import ru.skypro.coursework.courseworkeasyauction.model.BidModel;

public class BidMap {
    public static Bid mapToBid(BidModel bidModel){
        return Bid.builder()
                .bidderName(bidModel.getBidderName())
                .bidDate(bidModel.getBidDate())
                .build();
    }
}

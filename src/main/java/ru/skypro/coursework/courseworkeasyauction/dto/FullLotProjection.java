package ru.skypro.coursework.courseworkeasyauction.dto;


import ru.skypro.coursework.courseworkeasyauction.model.StatusLot;

public interface FullLotProjection {
    int getLotId();

    String getTitle();

    int getBidPrice();

    int getStartPrice();

    String getDescription();

    StatusLot getStatus();

    int getCurrentPrice();

    String getLastBidName();

    String getLastBidData();
}

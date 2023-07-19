package ru.skypro.coursework.courseworkeasyauction.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.skypro.coursework.courseworkeasyauction.model.StatusLot;


@Data
@AllArgsConstructor
@Builder
public class FullLot {
    private int lotId;
    private String title;
    private int bidPrice;
    private int startPrice;
    private String description;
    private StatusLot status;
    private int currentPrice;
    private String lastBidName;
    private String lastBidData;


}

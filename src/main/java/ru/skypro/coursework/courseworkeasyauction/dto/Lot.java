package ru.skypro.coursework.courseworkeasyauction.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.coursework.courseworkeasyauction.model.StatusLot;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lot {
    private StatusLot status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
}

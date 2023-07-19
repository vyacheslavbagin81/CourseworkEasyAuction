package ru.skypro.coursework.courseworkeasyauction.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateLot {
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
}

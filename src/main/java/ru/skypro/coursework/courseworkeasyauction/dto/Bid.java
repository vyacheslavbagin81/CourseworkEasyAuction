package ru.skypro.coursework.courseworkeasyauction.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid{
    private String bidderName;
    private LocalDateTime bidDate;
}

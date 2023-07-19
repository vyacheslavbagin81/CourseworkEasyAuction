package ru.skypro.coursework.courseworkeasyauction.service.lot;


import ru.skypro.coursework.courseworkeasyauction.dto.CreateLot;

public class CheckCreateLot {
    public static boolean checkCreateLot(CreateLot lot) {
        if (
                lot.getTitle().length() > 2 &&
                        lot.getTitle().length() < 65 &&
                        lot.getDescription().length() > 0 &&
                        lot.getDescription().length() < 4096 &&
                        lot.getStartPrice() > 0 &&
                        lot.getBidPrice() > 0
        ) {
            return true;
        }
        return false;
    }
}

package ru.skypro.coursework.courseworkeasyauction.service.lot;



import ru.skypro.coursework.courseworkeasyauction.dto.CreateLot;
import ru.skypro.coursework.courseworkeasyauction.dto.FullLot;
import ru.skypro.coursework.courseworkeasyauction.dto.Lot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public interface LotService {

    //    Получить полную информацию о лоте
    FullLot getFullLot(int id);

    //    Начать торги по лоту
    String startBidding(int id);



    //    Остановить торги по лоту
    String stopBidding(int id);

    //    Создает новый лот
    Lot createLot(CreateLot lot);

    //    Получить все лоты, основываясь на фильтре статуса и номере страницы
    Set<Lot> getSetLot(String status, int page);

    //    Экспортировать все лоты в файл CSV
    void getFile(PrintWriter writer) throws IOException;

}

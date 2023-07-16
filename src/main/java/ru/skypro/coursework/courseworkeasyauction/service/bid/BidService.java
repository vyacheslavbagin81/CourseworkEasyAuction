package ru.skypro.coursework.courseworkeasyauction.service.bid;


import ru.skypro.coursework.courseworkeasyauction.dto.Bid;

public interface BidService {

    //    Возвращает первого ставившего на этот лот
    Bid getBidFirstToId(int id);

    //    Возвращает имя ставившего на данный лот наибольшее количество раз
    Bid getBidFrequentToId(int id);

    //    Сделать ставку по лоту
    String placeABet(int id, String bidderName);
}

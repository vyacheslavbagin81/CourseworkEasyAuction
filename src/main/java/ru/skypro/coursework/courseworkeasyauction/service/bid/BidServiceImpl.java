package ru.skypro.coursework.courseworkeasyauction.service.bid;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.coursework.courseworkeasyauction.dto.Bid;
import ru.skypro.coursework.courseworkeasyauction.exeption.StatusException;
import ru.skypro.coursework.courseworkeasyauction.exeption.NotIdException;
import ru.skypro.coursework.courseworkeasyauction.model.BidModel;
import ru.skypro.coursework.courseworkeasyauction.model.LotModel;
import ru.skypro.coursework.courseworkeasyauction.model.StatusLot;
import ru.skypro.coursework.courseworkeasyauction.repository.BidRepository;
import ru.skypro.coursework.courseworkeasyauction.repository.LotRepository;
import ru.skypro.coursework.courseworkeasyauction.service.maping.BidMap;


@Data
@AllArgsConstructor
@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private final BidRepository bidRepository;
    @Autowired
    private final LotRepository lotRepository;

    private static final Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);


    @Override
    public Bid getBidFirstToId(int id) {
        logger.info("выполняется метод по выводу информации по первой ставке на лот с id = " + id);
        return BidMap.mapToBid(bidRepository.getFirstByBidDateContainingOrderByLotID(id).orElseThrow(NotIdException::new));
    }

    @Override
    public Bid getBidFrequentToId(int id) {
        logger.info("выполняется метод по выводу информации по игроку деловшему больше ставок на лот с id = " + id);
        return BidMap.mapToBid(bidRepository.getBidFrequentToId(id).orElseThrow(NotIdException::new));
    }
    @Override
    public String placeABet(int id, String bidderName) {
        logger.info("выполняется метод для того чтобы сделать ставку на лот с id = " + id);
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        if (lotModel.getStatus().equals(StatusLot.STARTED)) {
            BidModel bidModel = new BidModel(bidderName, lotModel);
            bidRepository.save(bidModel);
            logger.debug("ставка сохраненна в базе данных");
            return "Ставка создана " + bidderName;
        } else throw new StatusException();
    }
}

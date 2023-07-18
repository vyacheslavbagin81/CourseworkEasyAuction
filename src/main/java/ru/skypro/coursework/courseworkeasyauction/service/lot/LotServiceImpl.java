package ru.skypro.coursework.courseworkeasyauction.service.lot;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.coursework.courseworkeasyauction.dto.CreateLot;
import ru.skypro.coursework.courseworkeasyauction.dto.FullLot;
import ru.skypro.coursework.courseworkeasyauction.dto.Lot;
import ru.skypro.coursework.courseworkeasyauction.exeption.DataException;
import ru.skypro.coursework.courseworkeasyauction.exeption.NotIdException;
import ru.skypro.coursework.courseworkeasyauction.model.LotModel;
import ru.skypro.coursework.courseworkeasyauction.model.StatusLot;
import ru.skypro.coursework.courseworkeasyauction.repository.LotRepository;
import ru.skypro.coursework.courseworkeasyauction.service.maping.FullLotMap;
import ru.skypro.coursework.courseworkeasyauction.service.maping.LotMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@Service
public class LotServiceImpl implements LotService {
    private final LotRepository lotRepository;

    private final String[] HEADERS = {
            "lotId",
            "title",
            "description",
            "status",
            "startPrice",
            "bidPrice",
            "currentPrice",
            "lastBidName",
            "lastBidData"
    };
    private static final Logger LOGGER = LoggerFactory.getLogger(LotServiceImpl.class);


    @Override
    public FullLot getFullLot(int id) {
        LOGGER.info("выполняется метод для вывода полной информации о лоте по id");
        return FullLotMap.toFullLot(lotRepository.getFullLotById(id));
    }

    @Override
    public String startBidding(int id) {
        LOGGER.info("выполняется метод для старта торгов");
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        lotModel.setStatus(StatusLot.STARTED);
        lotRepository.save(lotModel);
        LOGGER.debug("изменен статус лота в базе данных");
        return "Лот переведен в статус начато";
    }


    @Override
    public String stopBidding(int id) {
        LOGGER.info("выполняется метод для остановки торгов");
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        lotModel.setStatus(StatusLot.STOPPED);
        lotRepository.save(lotModel);
        LOGGER.debug("изменен статус лота в базе данных");
        return "Лот перемещен в статус остановлен";
    }

    @Override
    public Lot createLot(CreateLot lot) {
        LOGGER.info("выполняется метод для создания лота");
        if (CheckCreateLot.checkCreateLot(lot)) {
            LotModel lotModel = LotMap.mapToLotModel(lot);
            lotRepository.save(lotModel);
            LOGGER.debug("лот сохранен в базу данных");
            return LotMap.mapToLot(lotModel);
        }
        throw new DataException();
    }

    @Override
    public Set<Lot> getSetLot(String status, int page) {
        LOGGER.info("выполняется метод для вывода информации о лотах в зависимости от указаного статуса и номера страницы базы данных");
        Pageable pageable = PageRequest.of(page, 10);
        Page<LotModel> lotModelPage = lotRepository.findAll(pageable);
        Set<Lot> lots = lotModelPage.stream().map(LotMap::mapToLot).collect(Collectors.toSet());
        return lots.stream().filter(lot -> lot.getStatus().name().equals(status)).collect(Collectors.toSet());
    }

    @Override
    public void getFile(PrintWriter writer) {
        LOGGER.info("выполняется метод для передачи данных о лотах в виде файла csv");
        List<FullLot> fullLotList = lotRepository.getFullLot().stream()
                .map(FullLotMap::toFullLot)
                .collect(Collectors.toList());
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();
        try (
                CSVPrinter printer = new CSVPrinter(writer, format)
        ) {
            printer.printRecords(fullLotList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

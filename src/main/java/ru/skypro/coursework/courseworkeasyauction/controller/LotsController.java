package ru.skypro.coursework.courseworkeasyauction.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.coursework.courseworkeasyauction.dto.FullLot;
import ru.skypro.coursework.courseworkeasyauction.dto.Lot;
import ru.skypro.coursework.courseworkeasyauction.dto.CreateLot;
import ru.skypro.coursework.courseworkeasyauction.service.lot.LotService;

import java.io.IOException;
import java.util.Set;


@Data
@RestController
@RequestMapping("/lot")
public class LotsController {
    private final LotService lotService;

    //    Получить полную информацию о лоте
    @GetMapping("/{id}")
    public FullLot getFullLot(@PathVariable int id) {
        return lotService.getFullLot(id);
    }

    //    Начать торги по лоту
    @PostMapping("/{id}/start")
    public ResponseEntity<?> startBidding(@PathVariable int id) {
        return new  ResponseEntity<>(lotService.startBidding(id), HttpStatus.OK);
    }



    //    Остановить торги по лоту
    @PostMapping("/{id}/stop")
    public ResponseEntity<?> stopBidding(@PathVariable int id) {
        return new  ResponseEntity<>(lotService.stopBidding(id), HttpStatus.OK);
    }

    //    Создает новый лот
    @PostMapping
    public Lot createLot(@RequestBody CreateLot lot) {
        return lotService.createLot(lot);
    }

    //    Получить все лоты, основываясь на фильтре статуса и номере страницы
    @GetMapping
    public Set<Lot> getSetLot(@RequestParam("status") String status,
                       @RequestParam("page") int page) {
        return lotService.getSetLot(status, page);
    }

    //    Экспортировать все лоты в файл CSV
    @GetMapping("/export")
    public void getFile(HttpServletResponse response) throws IOException {
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "lotFullInfo.csv" + "\"");
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/csv");
        lotService.getFile(response.getWriter());
    }
}

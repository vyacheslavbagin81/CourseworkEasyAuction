package ru.skypro.coursework.courseworkeasyauction.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.coursework.courseworkeasyauction.dto.Bid;
import ru.skypro.coursework.courseworkeasyauction.service.bid.BidService;

@Data
@RestController
@RequestMapping("/lot")
public class BidsController {
    private final BidService bidService;

    //    Возвращает первого ставившего на этот лот
    @GetMapping("/{id}/first")
    public Bid getBidFirstToId(@PathVariable int id) {
        return bidService.getBidFirstToId(id);
    }

    //    Возвращает имя ставившего на данный лот наибольшее количество раз
    @GetMapping("/{id}/frequent")
    public Bid getBidFrequentToId(@PathVariable int id) {
        return bidService.getBidFrequentToId(id);
    }

    //    Сделать ставку по лоту
    @PostMapping("/{id}/bid")
    public ResponseEntity<?> placeABet(@PathVariable int id, @RequestBody String bidderName) {
        return new  ResponseEntity<>(bidService.placeABet(id, bidderName), HttpStatus.OK);
    }

}

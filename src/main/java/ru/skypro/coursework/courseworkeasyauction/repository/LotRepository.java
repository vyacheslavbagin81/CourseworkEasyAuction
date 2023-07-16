package ru.skypro.coursework.courseworkeasyauction.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.coursework.courseworkeasyauction.dto.FullLotProjection;
import ru.skypro.coursework.courseworkeasyauction.model.LotModel;

import java.util.List;


@Repository
public interface LotRepository extends CrudRepository<LotModel, Integer>, PagingAndSortingRepository<LotModel, Integer> {


    @Query(value = "select l.lot_id as lotId, l.title as title, l.bid_price as bidPrice , l.start_price as startPrice , l.description as description , l.status as status, " +
            "(l.start_price + l.bid_price * (select count(b.lot_id) from bid b where b.lot_id = l.lot_id)) as  currentPrice, " +
            "b2.bidder_name as lastBidName, " +
            "b2.bid_date as lastBidData " +
            "from lot l inner join bid b2 on l.lot_id = ?1 and b2.bid_date = (select max(b3.bid_date)from bid b3 where b3.lot_id = ?1)",
            nativeQuery = true)
    FullLotProjection getFullLotById(int id);

    @Query(value = "select l.lot_id as lotId, l.title as title, l.bid_price as bidPrice , l.start_price as startPrice , l.description as description , l.status as status, " +
            "(l.start_price + l.bid_price * (select count(b.lot_id) from bid b where b.lot_id = l.lot_id)) as  currentPrice, " +
            "b2.bidder_name as lastBidName, " +
            "b2.bid_date as lastBidData " +
            "from lot l inner join bid b2 on l.lot_id = b2.lot_id and b2.bid_date = (select max(b3.bid_date)from bid b3 where b3.lot_id = l.lot_id)",
            nativeQuery = true)
    List<FullLotProjection> getFullLot();


}


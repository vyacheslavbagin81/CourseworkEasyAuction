package ru.skypro.coursework.courseworkeasyauction.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lot")
public class LotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private int idLot;

    @Column(columnDefinition = "varchar(16) default 'CREATED'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusLot status;

    //    minLength: 3
    //    maxLength: 64
    //    example: Chinese Vase
    @Size(min = 3, max = 64, message = "Не верный размер наименования")
    @Column(nullable = false)
    private String title;

    //    minLength: 1
//    maxLength: 4096
//    example: Chinese Vase of Ming dynasty
    @Size(min = 1, max = 4096, message = "Не верный объем описания")
    @Column
    private String description;

    //    minimum: 1
//    example: 100
//    Начальная цена лота
    @Min(value = 1, message = "Стартовая цена не может быть менее 1")
    @Column(name = "start_price", nullable = false)
    private Integer startPrice;


    //    minimum: 1
//    example: 100
//    Цена каждой новой ставки по
    @Min(value = 1, message = "Размер ставки не может быть менее 1")
    @Column(name = "bid_price")
    private Integer bidPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lot")
    @Fetch(FetchMode.SUBSELECT)
    private Set<BidModel> bidModels;
}

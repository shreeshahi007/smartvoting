package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="statement")
public class Statement {
    @Id
    @Column(name="statementId")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String statementId;

    @Column(name="statementContent")
    String statementContent;

    @Column(name="dateCreated")
    Date dateCreated=new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    double average;

}

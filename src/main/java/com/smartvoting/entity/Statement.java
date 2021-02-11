package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="statement")
public class Statement {
    @Id
    @Column(name="statement_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String statementId;

    @Column(name="statement_content")
    String statementContent;

    @Column(name="date_created")
    Date dateCreated=new Date();

    @Column(name = "room_id")
    String roomId;

    @Column(name = "is_active")
    boolean isActive;

    double average;

}

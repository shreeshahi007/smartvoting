package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="responses")
public class Responses {
    @Id
    @Column(name="responseId")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String responseId;


    @Column(name="statement_id")
    String statementId;

    @Column(name="responseValue")
    Integer responseValue;
}

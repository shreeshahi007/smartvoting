package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="responses")
public class Responses {
    @Id
    @Column(name="response_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String responseId;
    

    @Column(name="statement_id")
    String statementId;

    @Column(name="response_value")
    Integer responseValue;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    Guest guest;
}

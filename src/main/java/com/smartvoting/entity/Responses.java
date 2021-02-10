package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Target;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="statement_id", referencedColumnName = "statement_id")
    Statement statement;

    @Column(name="response_value")
    Integer responseValue;

    @ManyToOne (cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Guest guest;
}

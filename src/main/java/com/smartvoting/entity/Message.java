package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="message")
public class Message {
    @Id
    @Column(name="messageId")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String messageId;

    @ManyToOne
    @org.hibernate.annotations.Target(Room.class)
    @JoinColumn(name="room_id")
    Room room;

    @ManyToOne
    @org.hibernate.annotations.Target(Guest.class)
    @JoinColumn(name="guest_id")
    Guest guest;

    @Column(name="messageContent")
    String messageContent;

    @Column(name="dateCreated")
    Date dateCreated=new Date();

}

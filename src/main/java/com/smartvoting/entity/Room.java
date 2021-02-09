package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="room")
public class Room {
    @Id
    @Column(name="roomId")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String roomId;

    @Column(name="roomName")
    String roomName;

    @Column(name="password")
    String password;

    @Column(name="isActive")
    boolean isActive;

    @Column(name="roomDescription")
    String roomDescription;
}

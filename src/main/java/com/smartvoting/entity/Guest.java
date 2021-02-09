package com.smartvoting.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name="guest")
public class Guest {
    @Id
    @Column(name="guestId")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String guestId;

    @OneToOne
    @org.hibernate.annotations.Target(Room.class)
    @JoinColumn(name="room_id")
    Room room;

    @Column(name="guestName")
    String guestName;

    @Column(name="isAdmin")
    boolean isAdmin;

    @Column(name="photoUrl")
    String photoUrl;
}

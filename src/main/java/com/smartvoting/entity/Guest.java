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

//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)//update and delete from this both are getting updated
//    @JoinColumn(name = "room_id", referencedColumnName = "guestId")
//    Room room;

    @OneToOne(targetEntity = Room.class, cascade = CascadeType.ALL)
    Room room;

    @Column(name="guestName")
    String guestName;

    @Column(name="isAdmin")
    boolean isAdmin;

    @Column(name="photoUrl")
    String photoUrl;
}

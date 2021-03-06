package com.smartvoting.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Target;

import javax.persistence.*;

@Entity
@Data
@Table(name="guest")
public class Guest {
    @Id
    @Column(name="guest_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String guestId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="room_id")
    Room room;

    @Column(name="guest_name")
    String guestName;

    @Column(name="is_admin")
    boolean isAdmin;

    @Column(name="photo_url")
    String photoUrl;

}

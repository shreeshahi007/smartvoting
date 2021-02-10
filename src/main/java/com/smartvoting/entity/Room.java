package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="room")
public class Room {
    @Id
    @Column(name="room_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String roomId;

    @Column(name="room_name")
    String roomName;

    @Column(name="password")
    String password;

    @Column(name="is_active")
    boolean isActive;

    @Column(name="room_description")
    String roomDescription;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Set<Statement> statements = new HashSet<>();

}

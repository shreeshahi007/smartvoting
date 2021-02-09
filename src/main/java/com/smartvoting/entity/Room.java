package com.smartvoting.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Statement> statements;
}

package com.ssummit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkpoint_marks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckpointMark {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "checkpoint_id",
            foreignKey = @ForeignKey(name = "FK_CHECKPOINT")
    )
    private Checkpoint checkpoint;

    @Column(name = "scheduled_mark_time")
    private LocalDateTime scheduledMarkedTime;

    @Column(name = "actual_marked_time")
    private LocalDateTime actualMarkedTime;
}

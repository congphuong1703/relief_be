package com.relief.domain.models.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "communes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String code;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
}

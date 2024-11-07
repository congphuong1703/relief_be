package com.relief.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "increment_codes")
public class IncrementCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "increment_code_id")
    private Long incrementCodeId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "increment", nullable = false)
    private int increment;

    public IncrementCode(String code) {
        this.code = code;
        this.increment = 0;
    }
}

package com.LearnWithFun.PdfGenerator.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="mt_instrument",uniqueConstraints = @UniqueConstraint(columnNames = "instr_isin"))
public class Instrument {
    
    

    @CsvBindByName(column ="INSTR_ISIN")
    @Column(name="instr_isin")
    private String instrument_isin;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "your_sequence", allocationSize = 1)
    @Column(name ="instr_cd")
    private Integer instrument_code;
    
    
    @CsvBindByName(column="DESCRIPTION")
    @Column(name="instr_description")
    @Nonnull
    private String instr_description;
    
    @CsvBindByName(column="NOMINAL_VALUE")
    @Column(name="nominal_value")
    @Nonnull
    private double nominalValue;
    
    @CsvDate("dd-MM-yyyy")
    @CsvBindByName(column="ISSUE_DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate issueDate;
    
    @CsvDate("dd-MM-yyyy")
    @CsvBindByName(column="MATURITY_DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate maturityDate;
    
    @Column(name="totalnominal_value")
    private double totalNominalValue;
    
    
    @CsvBindByName(column="INSTR_TYPE")
    @Nonnull
    @Column(name="instr_type")
    private String instr_type;
    
    

    @PrePersist
    public void prePersist() {
          if (instrument_code == null) {
              instrument_code = 1000; // Set your desired starting point
          } else {
              instrument_code++;
          } 
    }
}
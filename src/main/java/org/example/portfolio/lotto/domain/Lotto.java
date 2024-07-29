package org.example.portfolio.lotto.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "lotto", schema = "lotto")
public class Lotto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int drawNumber;
  private int first;
  private int second;
  private int third;
  private int forth;
  private int fifth;
  private int sixth;
  private int bonus;
}

package org.example.portfolio.lotto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lotto_prediction", schema = "lotto")
public class LottoPrediction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int first;
  private int second;
  private int third;
  private int forth;
  private int fifth;
  private int sixth;

  @Override
  public String toString() {
    return "LottoPrediction{" +
        "id=" + id +
        ", first=" + first +
        ", second=" + second +
        ", third=" + third +
        ", forth=" + forth +
        ", fifth=" + fifth +
        ", sixth=" + sixth +
        '}';
  }
}

package org.example.portfolio.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class BaseTimeEntity {

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP(0)")
  private LocalDateTime createDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "update_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP(0)")
  private LocalDateTime updateDate;

  @PrePersist
  public void onPersist() {
    this.createDate = LocalDateTime.now();
    this.updateDate = LocalDateTime.now();
  }

  @PreUpdate
  public void onUpdate() {
    this.updateDate = LocalDateTime.now();
  }

}

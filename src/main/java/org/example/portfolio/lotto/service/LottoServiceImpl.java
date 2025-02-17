package org.example.portfolio.lotto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.lotto.domain.LottoPrediction;
import org.example.portfolio.lotto.repository.LottoPredictionRepository;
import org.example.portfolio.lotto.repository.LottoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoServiceImpl implements LottoService {

  private final LottoRepository lottoRepository;
  private final LottoPredictionRepository lottoPredictionRepository;

  @Override
  public void getLotto(int weekNumber) {
    // 1. 과거 데이터 기반 번호 출현 빈도 분석
    Map<Integer, Long> frequencyMap = calculateNumberFrequency();

    // 2. 최근 3회차 출현한 번호는 제외
    Set<Integer> excludeNumbers = getRecentNumbers();

    // 3. 빈도수 높은 번호에서 제외 번호를 뺀 나머지 번호 추출
    List<Integer> candidateNumbers = frequencyMap.entrySet().stream()
        .filter(entry -> !excludeNumbers.contains(entry.getKey()))
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

    // 4. 패턴 및 빈도수를 기반으로 5세트 생성
    List<List<Integer>> predictions = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Integer> numbers = generateWinningNumbers(weekNumber, candidateNumbers);
      predictions.add(numbers);
      savePrediction(numbers);
    }
  }

  /**
   * 생성된 번호를 DB에 저장
   */
  private void savePrediction(List<Integer> numbers) {
    LottoPrediction prediction = new LottoPrediction(numbers);
    lottoPredictionRepository.save(prediction);
  }

  /**
   * 번호 출현 빈도 분석
   */
  private Map<Integer, Long> calculateNumberFrequency() {
    List<Object[]> allNumbers = lottoRepository.findAllWinningNumbers();
    List<Integer> numberList = allNumbers.stream()
        .flatMap(Arrays::stream)
        .map(obj -> (Integer) obj)
        .toList();

    return numberList.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  /**
   * 최근 N회차의 번호 추출
   */
  private Set<Integer> getRecentNumbers() {
    Pageable pageable = PageRequest.of(0, 3);
    List<Object[]> recentNumbers = lottoRepository.findRecentWinningNumbers(pageable);

    return recentNumbers.stream()
        .flatMap(Arrays::stream)
        .map(obj -> (Integer) obj)
        .collect(Collectors.toSet());
  }

  /**
   * 후보 번호에서 패턴을 적용해 당첨 번호 생성
   */
  private List<Integer> generateWinningNumbers(int weekNumber, List<Integer> candidateNumbers) {
    List<Integer> numbers;
    if (weekNumber % 3 == 0) {
      numbers = candidateNumbers.subList(15, candidateNumbers.size());
    } else if (weekNumber % 3 == 1) {
      numbers = candidateNumbers.subList(0, 15);
    } else {
      numbers = candidateNumbers;
    }
    return pickRandomNumbers(numbers);
  }

  /**
   * 번호 6개 랜덤 선택 및 정렬
   */
  private List<Integer> pickRandomNumbers(List<Integer> numberPool) {
    Collections.shuffle(numberPool);
    return numberPool.stream().limit(6).sorted().collect(Collectors.toList());
  }
}
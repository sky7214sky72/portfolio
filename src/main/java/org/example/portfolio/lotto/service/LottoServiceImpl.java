package org.example.portfolio.lotto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.example.portfolio.lotto.repository.LottoPredictionRepository;
import org.example.portfolio.lotto.repository.LottoRepository;
import org.example.portfolio.lotto.domain.Lotto;
import org.example.portfolio.lotto.domain.LottoPrediction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoServiceImpl implements LottoService {

  private final LottoRepository lottoRepository;
  private final LottoPredictionRepository lottoPredictionRepository;

  @Async
  @Override
  public void getLotto() {
    List<Lotto> pastPicks = lottoRepository.findAll();

    // 번호 빈도 계산
    Map<Integer, Integer> numberCounts = new HashMap<>();
    for (Lotto pick : pastPicks) {
      int[] numbers = {pick.getFirst(), pick.getSecond(), pick.getThird(), pick.getForth(),
          pick.getFifth(), pick.getSixth()};
      for (int num : numbers) {
        numberCounts.put(num, numberCounts.getOrDefault(num, 0) + 1);
      }
    }

    // 자주 선택되는 번호와 드물게 선택되는 번호 구분
    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(numberCounts.entrySet());
    entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

    List<Integer> commonNumbers = new ArrayList<>();
    List<Integer> uncommonNumbers = new ArrayList<>();

    for (int i = 0; i < entries.size(); i++) {
      if (i < 20) {
        commonNumbers.add(entries.get(i).getKey());
      } else {
        uncommonNumbers.add(entries.get(i).getKey());
      }
    }
    List<LottoPrediction> lottoPredictions = new ArrayList<>();
    // 최적화 알고리즘을 사용하여 조합 찾기
    for (int i = 0; i<5;i++) {
      int[] bestCombination = findBestCombination(
          commonNumbers.stream().sorted(Comparator.naturalOrder()).toList(),
          uncommonNumbers.stream().sorted(Comparator.naturalOrder()).toList(),
          pastPicks);
      LottoPrediction lottoPrediction = LottoPrediction.builder()
          .first(bestCombination[0])
          .second(bestCombination[1])
          .third(bestCombination[2])
          .forth(bestCombination[3])
          .fifth(bestCombination[4])
          .sixth(bestCombination[5])
          .build();
      lottoPredictions.add(lottoPrediction);
    }
    lottoPredictionRepository.saveAll(lottoPredictions);
  }

  private int[] findBestCombination(List<Integer> common, List<Integer> uncommon,
      List<Lotto> pastPicks) {
    Random random = new Random();
    int[] bestCombination = null;
    int bestWinnerCount = Integer.MAX_VALUE;

    // 유전 알고리즘을 사용한 구현
    for (int i = 0; i < 10000000; i++) {
      int[] combination = generateCombination(common, uncommon, random);
      int winnerCount = simulateWinners(combination, pastPicks);

      // 5~10명의 당첨자가 나오는 조합을 찾기 위해 최적화
      if (winnerCount >= 5 && winnerCount <= 10) {
        bestCombination = combination;
        break;  // 목표에 부합하면 조합을 반환
      }

      // 더 나은 조합을 찾으면 갱신
      if (Math.abs(winnerCount - 7) < Math.abs(bestWinnerCount - 7)) {
        bestCombination = combination;
        bestWinnerCount = winnerCount;
      }
    }

    return bestCombination;
  }

  private int[] generateCombination(List<Integer> common, List<Integer> uncommon, Random random) {
    Set<Integer> combinationSet = new HashSet<>();
    while (combinationSet.size() < 4) {
      combinationSet.add(common.get(random.nextInt(common.size())));
    }
    while (combinationSet.size() < 6) {
      combinationSet.add(uncommon.get(random.nextInt(uncommon.size())));
    }
    int[] combination = new int[6];
    int index = 0;
    for (int num : combinationSet) {
      combination[index++] = num;
    }
    return combination;
  }

  private int simulateWinners(int[] combination, List<Lotto> pastPicks) {
    int count = 0;
    Set<Integer> combinationSet = new HashSet<>();
    for (int num : combination) {
      combinationSet.add(num);
    }
    for (Lotto pick : pastPicks) {
      Set<Integer> pickSet = new HashSet<>(
          Arrays.asList(pick.getFirst(), pick.getSecond(), pick.getThird(), pick.getForth(),
              pick.getFifth(), pick.getSixth(), pick.getBonus()));
      if (combinationSet.containsAll(pickSet)) {
        count++;
      }
    }
    return count;
  }
}
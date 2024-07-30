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
import org.example.portfolio.lotto.application.in.LottoPort;
import org.example.portfolio.lotto.application.out.LottoPredictionRepository;
import org.example.portfolio.lotto.application.out.LottoRepository;
import org.example.portfolio.lotto.domain.Lotto;
import org.example.portfolio.lotto.domain.LottoPrediction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoService implements LottoPort {

  private final LottoRepository lottoRepository;
  private final LottoPredictionRepository lottoPredictionRepository;

  @Override
  public int[] getLotto() {
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

    // 번호 조합 생성 및 선택
    Random random = new Random();
    int[] bestCombination = generateCombination(commonNumbers, uncommonNumbers, random);
    LottoPrediction lottoPrediction = LottoPrediction.builder()
        .first(bestCombination[0])
        .second(bestCombination[1])
        .third(bestCombination[2])
        .forth(bestCombination[3])
        .fifth(bestCombination[4])
        .sixth(bestCombination[5])
        .bonus(bestCombination[6])
        .build();
    lottoPredictionRepository.save(lottoPrediction);
    return bestCombination;
  }

  private int[] generateCombination(List<Integer> common, List<Integer> uncommon, Random random) {
    Set<Integer> combinationSet = new HashSet<>();
    while (combinationSet.size() < 4) {
      combinationSet.add(common.get(random.nextInt(common.size())));
    }
    while (combinationSet.size() < 7) {
      combinationSet.add(uncommon.get(random.nextInt(uncommon.size())));
    }
    int[] combination = new int[7];
    int index = 0;
    for (int num : combinationSet) {
      combination[index++] = num;
    }
    return combination;
  }
}
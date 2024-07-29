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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

    // 번호 선택 빈도 계산
    Map<Integer, Integer> numberCounts = new HashMap<>();
    for (Lotto pick : pastPicks) {
      int[] numbers = {pick.getFirst(), pick.getSecond(), pick.getThird(), pick.getForth(), pick.getFifth(), pick.getSixth()};
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

    // 목표 당첨자 수 설정
    int targetMinWinners = 5;
    int targetMaxWinners = 10;

    Random random = new Random();
    int[] bestCombination = null;
    int bestWinnerCount = Integer.MAX_VALUE;

    // 병렬 처리 설정
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
    List<Future<Result>> futures = new ArrayList<>();

    // 병렬 작업 제출
    for (int i = 0; i < availableProcessors; i++) {
      futures.add(executorService.submit(new LottoTask(commonNumbers, uncommonNumbers, pastPicks, targetMinWinners, targetMaxWinners, 80000000 / availableProcessors, random)));
    }

    // 결과 처리
    for (Future<Result> future : futures) {
      try {
        Result result = future.get();
        if (result.getWinnerCount() >= targetMinWinners && result.getWinnerCount() <= targetMaxWinners) {
          bestCombination = result.getCombination();
          bestWinnerCount = result.getWinnerCount();
          break;
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    executorService.shutdown();

    System.out.println("선택된 조합: " + Arrays.toString(bestCombination));
    System.out.println("예상 당첨자 수: " + bestWinnerCount);
    if (bestCombination != null) {
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
    }

    return bestCombination;
  }

  static int[] generateCombination(List<Integer> common, List<Integer> uncommon, Random random) {
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

  static int simulateWinners(int[] combination, List<Lotto> pastPicks) {
    int count = 0;
    Set<Integer> combinationSet = new HashSet<>();
    for (int num : combination) {
      combinationSet.add(num);
    }
    for (Lotto pick : pastPicks) {
      Set<Integer> pickSet = new HashSet<>(Arrays.asList(pick.getFirst(), pick.getSecond(), pick.getThird(), pick.getForth(), pick.getFifth(), pick.getSixth()));
      if (combinationSet.equals(pickSet)) {
        count++;
      }
    }
    return count;
  }
}

class LottoTask implements Callable<Result> {
  private final List<Integer> commonNumbers;
  private final List<Integer> uncommonNumbers;
  private final List<Lotto> pastPicks;
  private final int targetMinWinners;
  private final int targetMaxWinners;
  private final int iterations;
  private final Random random;

  public LottoTask(List<Integer> commonNumbers, List<Integer> uncommonNumbers, List<Lotto> pastPicks, int targetMinWinners, int targetMaxWinners, int iterations, Random random) {
    this.commonNumbers = commonNumbers;
    this.uncommonNumbers = uncommonNumbers;
    this.pastPicks = pastPicks;
    this.targetMinWinners = targetMinWinners;
    this.targetMaxWinners = targetMaxWinners;
    this.iterations = iterations;
    this.random = random;
  }

  @Override
  public Result call() {
    int[] bestCombination = null;
    int bestWinnerCount = Integer.MAX_VALUE;

    for (int i = 0; i < iterations; i++) {
      int[] combination = LottoService.generateCombination(commonNumbers, uncommonNumbers, random);
      int winnerCount = LottoService.simulateWinners(combination, pastPicks);

      if (targetMinWinners <= winnerCount && winnerCount <= targetMaxWinners) {
        bestCombination = combination;
        bestWinnerCount = winnerCount;
        break;
      }
    }

    return new Result(bestCombination, bestWinnerCount);
  }
}

class Result {
  private final int[] combination;
  private final int winnerCount;

  public Result(int[] combination, int winnerCount) {
    this.combination = combination;
    this.winnerCount = winnerCount;
  }

  public int[] getCombination() {
    return combination;
  }

  public int getWinnerCount() {
    return winnerCount;
  }
}
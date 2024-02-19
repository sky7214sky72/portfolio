package org.example.portfolio.user.application.port.out;

import java.util.List;
import org.example.portfolio.user.adapter.in.dto.response.GetMemorizedWordResponse;

public interface UserMemorizedWordCustom {

  List<GetMemorizedWordResponse> getMemorizedWord(Long userId);
}

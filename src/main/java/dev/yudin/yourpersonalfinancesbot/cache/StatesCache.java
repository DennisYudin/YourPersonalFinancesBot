package dev.yudin.yourpersonalfinancesbot.cache;

import dev.yudin.yourpersonalfinancesbot.enums.State;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatesCache implements Cache {

	private final Map<Long, State> usersStates = new HashMap<>();

	@Override
	public void saveState(long userId, State state) {
		usersStates.put(userId, state);
	}

	@Override
	public State defineUserStateBy(long userId) {
		return usersStates.getOrDefault(userId, State.MAIN_MENU);
	}
}

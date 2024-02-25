package dev.yudin.yourpersonalfinancesbot.cache;

import dev.yudin.yourpersonalfinancesbot.enums.State;

public interface Cache {

	void saveState(long userId, State state);

	State defineStateBy(long userId);
}

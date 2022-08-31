package dev.yudin.YourPersonalFinancesBot.cache;

import dev.yudin.YourPersonalFinancesBot.enums.State;

public interface Cache {

	void saveState(long userId, State state);

	State defineStateBy(long userId);
}

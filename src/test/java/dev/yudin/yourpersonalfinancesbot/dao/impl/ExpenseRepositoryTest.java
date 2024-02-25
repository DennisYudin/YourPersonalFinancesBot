package dev.yudin.yourpersonalfinancesbot.dao.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import dev.yudin.yourpersonalfinancesbot.dao.ExpenseRepository;
import dev.yudin.yourpersonalfinancesbot.entities.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Sql(scripts = {
		"file:src/test/resources/cleanUpTables.sql",
		"file:src/test/resources/createTables.sql",
		"file:src/test/resources/populateTables.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "file:src/test/resources/cleanUpTables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
class ExpenseRepositoryTest {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Test
	void findAll() {

		List<Expense> actual = expenseRepository.findAll();

		Expense expenseOne = new Expense("Food", 1000, "Card", 1441718175,
				LocalDateTime.of(
				2021, 8, 13,
				21, 23, 0
		).atZone(ZoneId.systemDefault()));
		expenseOne.setId(1);

		Expense expenseTwo = new Expense("Personal", 350, "Cash", 2441718176L,
				LocalDateTime.of(
				2019, 2, 10,
				8, 30, 15
		).atZone(ZoneId.systemDefault()));
		expenseTwo.setId(2);

		List<Expense> expected = List.of(expenseOne, expenseTwo);

		assertIterableEquals(expected, actual);
	}

	@Test
	void save() {

		Expense newExpense = new Expense("Personal", 100, "Cash", 2441718176L,
				LocalDateTime.of(
						2019, 02, 10,
						8, 30, 15
				).atZone(ZoneId.systemDefault()));
		newExpense.setId(0);

		expenseRepository.save(newExpense);

		Expense actual = null;
		Optional<Expense> expenseOptional = expenseRepository.findById(3);
		if (expenseOptional.isPresent()) {
			actual = expenseOptional.get();
	    }
		assertEquals(newExpense, actual);
	}
}

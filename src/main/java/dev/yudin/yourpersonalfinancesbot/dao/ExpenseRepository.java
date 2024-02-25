package dev.yudin.yourpersonalfinancesbot.dao;

import dev.yudin.yourpersonalfinancesbot.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}

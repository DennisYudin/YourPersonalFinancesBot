package dev.yudin.yourpersonalfinancesbot.dao;

import dev.yudin.yourpersonalfinancesbot.entities.Expense;

import java.util.List;

public interface DAO {

	List<Expense> findAll();
}

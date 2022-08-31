package dev.yudin.YourPersonalFinancesBot.dao.impl;

import dev.yudin.YourPersonalFinancesBot.dao.DAO;
import dev.yudin.YourPersonalFinancesBot.entities.Expense;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

@Log4j
@Repository("expenseDAO")
public class ExpenseDAOImpl implements DAO {

	private SessionFactory factory;
	private EntityManager entityManager;

	@Autowired
	public ExpenseDAOImpl(SessionFactory factory,
						  EntityManager entityManager) {
		this.factory = factory;
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Expense> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Expense> query = currentSession.createQuery("from ExpenseDTO", Expense.class);

		return query.getResultList();
	}

	public void save(Expense expense) {
		log.info(expense + " saved into database!");
	}
}

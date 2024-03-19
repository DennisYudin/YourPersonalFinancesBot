package dev.yudin.yourpersonalfinancesbot.entities;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String expenseName;
    @Column(name = "sum")
    private int sum;
    @Column(name = "type")
    private String expenseType;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "date")
    private ZonedDateTime date;

    public Expense() {
    }

    public Expense(String expenseName, int sum, String expenseType, long userId, ZonedDateTime date) {
        this.expenseName = expenseName;
        this.sum = sum;
        this.expenseType = expenseType;
        this.userId = userId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (id != expense.id) return false;
        if (sum != expense.sum) return false;
        if (userId != expense.userId) return false;
        if (!expenseName.equals(expense.expenseName)) return false;
        if (!expenseType.equals(expense.expenseType)) return false;
        return date.equals(expense.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + expenseName.hashCode();
        result = 31 * result + sum;
        result = 31 * result + expenseType.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseName='" + expenseName + '\'' +
                ", sum=" + sum +
                ", expenseType='" + expenseType + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}


CREATE TYPE expense_category AS ENUM (
    'Medical/Healthcare',
    'Food',
    'Housing',
    'Transportation',
    'Personal',
    'Utilities',
	'Clothing',
	'Education',
	'Entertainment',
	'Other'
);

CREATE TYPE card_cash AS ENUM (
    'Card',
    'Cash'
);

CREATE TABLE expense (
	id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    expense_name expense_category NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL,
	expense_type card_cash,
    sum INT NOT NULL
);

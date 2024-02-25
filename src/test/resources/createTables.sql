
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

CREATE TYPE expense_type AS ENUM (
    'Card',
    'Cash'
);

CREATE TABLE expense (
	id SERIAL PRIMARY KEY,
    name expense_category NOT NULL,
    sum INT NOT NULL,
	type expense_type,
    user_id BIGINT NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL
);

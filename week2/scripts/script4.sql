INSERT INTO project0.accounts (balance) VALUES (15);

INSERT INTO project0.users_accounts_jt (account, owner) VALUES (1, 1);

INSERT INTO project0.accounts (balance) VALUES (500);

INSERT INTO project0.users_accounts_jt (account, owner) VALUES (2, 1);

SELECT project0.users.id, project0.users.username, project0.users.password, project0.users.role,
 project0.accounts.id AS account_id, project0.accounts.balance FROM project0.users 
LEFT JOIN project0.users_accounts_jt ON project0.users.id = project0.users_accounts_jt.owner 
LEFT JOIN project0.accounts ON project0.accounts.id = project0.users_accounts_jt.account;
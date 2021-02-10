# SQL

SQL = Structured Query Language

It is NOT a programming language, it is a "Query" language. It's a language that we use to perform operations against a relational database.

## SQL Dialects
- **PostgreSQL**
- OracleSQL
- MySQL
- Microsoft SQL Server
- SQLite
- MariaDB
- etc
- Each of these is a specific implementation of SQL
    - They all follow the SQL standard, so have many similarities

Database is an organized collection of data, stored in an organized format.

- Allows us to input, manage, organize, and retrieve data 
- Traditionally, it is organized into "tables" with "records" as rows and "fields" as columns
- "Data" is the intersection between rows and columns (or an individual cell)

RDBMS = Relational DataBase Management System
- A system that upholds specified relationships between tables
- Include functions that maintain the security, accuracy, integrity, and consistency of the data

## SQL Sublanguages
- DDL = Data Definition Language
    - For creation/alteration of table structure
    - CREATE, ALTER, TRUNCATE (Removes ALL data within a table), DROP (Delete table)
- DML = Data Manipulation Language
    - For changing/manipulation/modification of the data within a table
    - In regards to CRUD Operations (Create, Read, Update, Delete)
    - INSERT, SELECT, UPDATE, DELETE
- DQL = Data Query Language
    - Pertains to the Reading of the data
    - SELECT
    - Some consider this to not be a real sublanguage
- DCL = Data Control Language
    - Regulating access to the data by imposing restrictions like user permissions
    - Pertains to security
    - GRANT, REVOKE
- TCL = Transaction Control Language
    - Pertains to work on the database, and finalizing or reverting changes
    - COMMIT, ROLLBACK, SAVEPOINT, SET TRANSACTION (used to set isolation levels)

## Constraints

Constraint is a rule (or condition) to follow for enforcing database table relationships.

1. Primary Key
    - Data in this column is unique and not null
    - Acts as the unique identifier for records in this table
    - All tables in SQL MUST have a Primary Key
    - A composite key is just a Primary Key that consists of multiple columns together
        - Order is important
2. Foreign Key
    - Data in this column references the primary key of another table
    - Establishes relationships between 2 columns in the same table or different tables
    - A JOIN operation across a foreign key relationship within the same table is called a "Self Join"
3. Unique
    - Data in this column may not have duplicates
    - May still have multiple null values
4. Not Null
    - Enforces that data in this column is not empty (null)
5. Default
    - Provide a default value in case a value was not already provided
    - Generally when a new record is INSERTed
6. Check
    - Adds an extra condition (logical condition) that must be enforced
    - Such as age >= 18

## Cardinality / Multiplicity

Describes the numerical relationship between 2 tables. There are 3 categories:

- 1 to 1
    - Individual records in one table are associated with only 1 record in the other
    - Can be created by using a unique foreign key constraints
    - Note that the foreign key may be on either side of the relationship
- 1 to many (or many to 1)
    - Individual records in one table are associated with many records in the other
    - Ex: Bees to Beehives
    - Accomplished with a non-unique foreign key
    - Note that the foreign key MUST be on the many side of the relationship
- many to many
    - Records in both tables are associated with many records in the other
    - Ex: Students and Courses or Students and Professors
    - We construct this relationship using a join table

Join tables are a separate, independent table. It consists of 2 columns, both of which are foreign keys back to the original source tables. Both of these columns will be non-unique foreign keys, to effectively structure this table as two 1 to many relationships back to back. Neither source table will contain any foreign keys to the other table in regards to this relationship.

Table1 PK      |
:--------------|
1              |
2              |
3              |
4              |

Table2 PK      |
:--------------|
10             |
20             |

Join Table FK1 |Join Table FK2 |
:--------------|---------------|
1              |10             |
1              |20             |
2              |10             |
4              |20             |
3              |10             |

Example join table name: users_accounts_jt
The primary key of the join table is commonly structured (but not always) as a composite key over both columns.

## Normalization

Database Normalization = Design Pattern for databases that aims to reduce both duplicate data and redundancy.

Atomic Data = Data that has been broken down into the smallest possible meaningful unit
Ex: Multiple phone numbers should be separated into different records/cells

Levels of Normalization
- 0NF = Zero Normal Form
    - No Normalization whatsoever
    - Absolute Chaos
- 1NF = 1st Normal Form
    - All data must be atomic
    - Table should have a primary key
- 2NF = 2nd Normal Form
    - 1NF + No Partial Dependencies
    - All records must be identified by a single column (no composite keys)
- 3NF = 3rd Normal Form
    - 2NF + No Transitive Dependencies
    - No column is dependent upon a column that is not the Primary Key
    - Ex of Transitive Dependencies:
        - supervisorId & supervisorName
        - supervisorName is NOT dependent upon the PK of the employees table
        - It is dependent upon the supervisorId column
- There are always more normalization levels
- There are complicated nuanced levels that go on even beyond 6NF for example
- However, they are out of scope of this training

## Transactions

A transaction is a unit of work performed against a database. It is the propagation of one or more changes to the database. Generally, they are UPDATE, DELETE, and INSERT operations.

A transaction would be used if you want to group multiple operations together and execute them all at once.

### ACID (Properties of Transactions)

A. Atomicity
    - The transaction occurs 100% successfully or not at all
    - If it fails, the transaction will be aborted and changes will be rolled back
C. Consistency
    - Ensure that the database properly changes state upon a successfully committed transaction
    - No transaction should have any adverse effect on the data residing in the database
        - Referential Integrity must be maintained
        - Not just Foreign Key relationships, but ALL constraints
I. Isolation
    - Enables transactions to operate independently of each other
    - Provides the ability to support parallel processing for performance benefits
    - Some issues could potentially arise due to this behavior
        - Relates to the SET TRANSACTION in regards to *isolation levels*
D. Durability
    - Ensures that the result or effect of a committed transactions persists in case of system failure
    - There is no delay between a transaction completing and the state being permanently persisted
        - There is no staging state or anything

### Transaction Problems

There are some issues that can arise while following the Isolation property of transactions. We try to handle transactions concurrently, because it is faster. But what might happen if one transaction reads data from another transaction, but then the second transaction gets rolled back? We have just retrieved data that should not have existed at all.

- Dirty Read
    - When a transaction reads data that has been added by a different transaction that has yet to be committed
        - It does not matter whether the 2nd transaction will be successful or not
        - Simply reading data from a currently incomplete transaction counts as a dirty read
- Non-Repeatable Read
    - When a transaction re-reads data that it has previously read and finds another committed transaction has modified the data
- Phantom Read
    - When a transaction re-runs a query to find that the number of records has changed
    - Another committed transaction has INSERTED or DELETED records

### Transaction Isolation Levels

- The degree to which two transactions will interact with each other over the same data
- As our application becomes more complex, we must account for transactions that may occur at the same time
- The higher the Isolation Level, the more careful the system is about avoiding conflicts, but the locking overhead can increase as concurrency decreases
    - It reduces performance

Isolation Level |    Dirty Read    | Non-Repeatable Read |   Phantom Read   |
:---------------|------------------|---------------------|------------------|
Read Uncommitted|:heavy_check_mark:|:heavy_check_mark:   |:heavy_check_mark:|
Read Committed  |                  |:heavy_check_mark:   |:heavy_check_mark:|
Repeatable Read |                  |                     |:heavy_check_mark:|
Serializable    |                  |                     |                  |

- Read Committed Level has a rather minor performance hit
    - *Read Committed* is the default Isolation for PostgreSQL, and for most SQL Dialects
- Repeatable Read has a significant performance loss, but not catastrophically
- Serializable prevents *any* concurrency whatsoever
    - A *very* significant performance loss, since there is no longer any concurrency
    - Nothing can go wrong, because there is no concurrency

## Database Joins

- Operations that allow us to bring together data from multiple tables
- Particularly useful in describing many to many relationships
- There are several types
    - Inner Join
        - Only show records with the compared value existing in both tables
    - Full Outer Join
        - Fully join all tables together, substituting null values where data is absent
    - Left/Right Join
        - All data from Left/Right table paired with the other and substituting null values where appropriate
    - Cross Join
        - Cartesian Cross Product on records from both tables, so you get a list of combinations from the tables
        - This can generate a LOT of data
    - Self Join
        - Not truly its own category
        - Any join that operates on only 1 table

We will obtain a single result with columns from both tables, and records will be joined overlapping on one value from a single column (the FK column).

Most particularly, this overlapping column will map to the other table's primary key.
Some SQL Dialects enforce that Joins can only be performed through foreign keys to primary keys, but not all dialects do.

```SQL
SELECT <columns> FROM <left> <JOIN TYPE> JOIN <right>
    ON <left.column> = <right.column>;

SELECT * FROM users INNER JOIN accounts
    ON users.id = accounts.ownerId;
```

In the above example, `ownerId` was a FK that references the PK of the users table.
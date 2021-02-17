# ORM

Object Relational Mapper. A mapping between the Object-Oriented Java architecture to the relational database.
Classes in Java get mapped to tables in the DB.
Objects in Java get mapped to records in the DB.
HAS-A relationships (such as fields in a class) become foreign keys in the DB.

Hibernate in particular is an implementation of an ORM.

Hibernate follows JPA (Java Persistence API).

It will be important for us to use this collection of annotations as part of the design of our model classes.
From there, Spring Data can take that information, and perform all of the data access that we need.

## JPA
- @Entity
    - Terminology that Hibernate uses to describe classes that are mapped to a DB
    - Defines the class to be tracked by Hibernate or another ORM
- @Table
    - Not required
    - Allows us to define the table name that the class gets mapped to
    - If not used, Hibernate will default to the same table name as the class
        - Case insensitive
- @Id
    - Define which field will act as the primary key of the table
- @Column
    - Not required
    - Allows us to define the column name that specific fields will use
    - If not used, Hibernate will default to the same column name as the field
- Mapping annotations
    - For HAS-A relationships
    - @OneToOne
    - @OneToMany
        - @ManyToOne
    - @ManyToMany
        - @JoinTable
        - @JoinColumn
- etc

## Hibernate's API
- Session (Interface)
    - Similar to the idea of a JDBC Connection
    - Is an abstraction layer for a JDBC Connection
    - A "Session" can last longer than a single Connection
        - Sessions can use multiple Connections over time, if needed
- SessionFactory (Interface)
    - Used to build sessions (obtained by Configuration object)
- Configuration (Class)
    - Represents all of the information in the xml configuration file
- Transaction (Interface)
    - Represents a transaction within the DB
    - Useful methods like `rollback` and `commit`

Hibernate has many means of querying data. It does not necessarily require raw native SQL.

HQL: Hibernate Query Language. Similar to SQL, but abstracts a few aspects of SQL.
In particular, instead of referring to column names/foreign keys, you refer to data by the Class and field names. Which is quite handy, when you have many nested HAS-A relationships.
(e.g. `User.role.id`)

Hibernate does also support nativeSQL if you wanted.

Also has a very extensive/complicated Criteria Query API. An Object Oriented approach to querying.

### Note

Hibernate and other ORMs work quite well with HAS-A relationships. However, they don't operate very conveniently with IS-A relationships. It is possible to work with ORMs with inheritance, but it becomes far more complicated. So, generally, it is recommended to not use inheritance for your model classes unless it becomes critically necessary later on in development.
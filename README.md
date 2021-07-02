# UniversalDatabase Package

## Package Use:

```java
import universal.database.DatabaseMySql;
```

## Create Connection :

```java
DatabaseMySql sql = new DatabaseMySql("localhost:3306","dbName","username","password");
```

## Constructor:

```java
DatabaseMySql(String url,String db,String user,String pass)
```

> Methods:

1. `select(String ... columns)`

- You can pass multiple arguments also as columns names which you want to take result, by defaut it select all columns.

- Return ```DatabaseMySql``` Object.

Example:
 ```java
	ResultSet rs = sql.select().from("tablename").srun();
 	ResultSet rs = sql.select("column1").from("tablename").srun();
    ResultSet rs = sql.select("column1","column2").from("tablename").srun();
    ResultSet rs = sql.select("column1","column2","column3").from("tablename").srun();
    ResultSet rs = sql.select("column1","column2","column4").from("tablename").srun();
```

2. `from(String tablename)`

- Used with select command.

- ReturnType ```DatabaseMySql``` Object.

3. `srun()`

- ReturnType ```ResultSet```

4. `pInsert(String tablename,int unknwons,String ... parameters)`

- It return the prepared statement of INSERT command.

- ReturnType PreparedStatement.

```java
PreparedStatement ptmt = sql.pInsert("tablename",(int )No_Of_Unknown_Parameters);
PreparedStatement ptmt = sql.pInsert("tablename",1,"column1");
PreparedStatement ptmt = sql.pInsert("tablename",2,"column1",column3);
PreparedStatement ptmt = sql.pInsert("tablename",3,"column1","column3","column2");
```

Above Method Return Query :
```sql
Query : INSERT into tablename(column1,column3,column2) values (?,?,?);
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
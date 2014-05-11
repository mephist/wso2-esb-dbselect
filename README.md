DBSelect Mediator
=================

The DBSelect Mediator can execute an arbitrary SQL select statement and then set a resulting values as an OM-typed local message property on the message context. The DB connection used may be looked up from an external data source or specified inline. In this case, an Apache DBCP connection pool is established and used.

Unlike standard [DBLookup Mediator](https://docs.wso2.org/display/ESB481/DBLookup+Mediator) DBSelect can return a result set with multiple rows.
 
**Usage:**

Build the mediator and put its JAR into into:

```
$ESB_HOME/repository/components/dropins
```

**Syntax:**

```xml
<dbselect>
   <connection>
      <pool>
       (
        <driver/>
        <url/>
        <user/>
        <password/>
 
        <dsName/>
        <icClass/>
        <url/>
        <user/>
        <password/>
       )
        <property name="name" value="value"/>*
      </pool>
   </connection>
   <statement>
      <sql>SELECT something FROM table WHERE something_else = ?</sql>
      <parameter [value="" | expression=""] type="CHAR|VARCHAR|LONGVARCHAR|NUMERIC|DECIMAL|BIT|TINYINT|SMALLINT|INTEGER|BIGINT|REAL|FLOAT|DOUBLE|DATE|TIME|TIMESTAMP"/>*
      <result name="string" column="int|string"/>*
   </statement>+
</dbselect>
```
Result set would be stored as DB_SELECT_RESULT property.

**TODO:**:
* UI configuration


package com.shytong.core.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sytong
 * @Package com.shytong.core.database
 * @Description:
 * @date 2017-12-2714:06
 */
public abstract class  AbstractCommSQL<T>{
    private static final String AND = ") \nAND (";
    private static final String OR = ") \nOR (";

    private SQLStatement sql = new SQLStatement();
    public abstract T getSelf();
    public T update(String table) {
        sql().statementType = SQLStatement.StatementType.UPDATE;
        sql().tables.add(table);
        return getSelf();
    }
    public T set(String sets) {
        sql().sets.add(sets);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T set(String... sets) {
        sql().sets.addAll(Arrays.asList(sets));
        return getSelf();
    }

    public T insert_into(String tableName) {
        sql().statementType = SQLStatement.StatementType.INSERT;
        sql().tables.add(tableName);
        return getSelf();
    }

    public T values(String columns, String values) {
        sql().columns.add(columns);
        sql().values.add(values);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T into_columns(String... columns) {
        sql().columns.addAll(Arrays.asList(columns));
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T into_values(String... values) {
        sql().values.addAll(Arrays.asList(values));
        return getSelf();
    }



    public T select(String columns) {
        sql().statementType = SQLStatement.StatementType.SELECT;
        sql().select.add(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T select(String... columns) {
        sql().statementType = SQLStatement.StatementType.SELECT;
        sql().select.addAll(Arrays.asList(columns));
        return getSelf();
    }

    public T selectDistinct(String columns) {
        sql().distinct = true;
        select(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T selectDistinct(String... columns) {
        sql().distinct = true;
        select(columns);
        return getSelf();
    }


    public T deleteFrom(String table) {
        sql().statementType = SQLStatement.StatementType.DELETE;
        sql().tables.add(table);
        return getSelf();
    }

    private T from(String table) {
        sql().tables.add(table);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T from(String... tables) {
        sql().tables.addAll(Arrays.asList(tables));
        return getSelf();
    }

    public T join(String join) {
        sql().join.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T join(String... joins) {
        sql().join.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T innerJoin(String join) {
        sql().innerJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T innerJoin(String... joins) {
        sql().innerJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T leftOuterJoin(String join) {
        sql().leftOuterJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T leftOuterJoin(String... joins) {
        sql().leftOuterJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T rightOuterJoin(String join) {
        sql().rightOuterJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T rightOuterJoin(String... joins) {
        sql().rightOuterJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T outerJoin(String join) {
        sql().outerJoin.add(join);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T outerJoin(String... joins) {
        sql().outerJoin.addAll(Arrays.asList(joins));
        return getSelf();
    }

    public T where(String conditions) {
        sql().where.add(conditions);
        sql().lastList = sql().where;
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T where(String... conditions) {
        sql().where.addAll(Arrays.asList(conditions));
        sql().lastList = sql().where;
        return getSelf();
    }

    public T or() {
        sql().lastList.add(OR);
        return getSelf();
    }

    public T and() {
        sql().lastList.add(AND);
        return getSelf();
    }

    public T group_by(String columns) {
        sql().groupBy.add(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T group_by(String... columns) {
        sql().groupBy.addAll(Arrays.asList(columns));
        return getSelf();
    }

    public T having(String conditions) {
        sql().having.add(conditions);
        sql().lastList = sql().having;
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T having(String... conditions) {
        sql().having.addAll(Arrays.asList(conditions));
        sql().lastList = sql().having;
        return getSelf();
    }

    public T order_by(String columns) {
        sql().orderBy.add(columns);
        return getSelf();
    }

    /**
     * @since 3.4.2
     */
    public T order_by(String... columns) {
        sql().orderBy.addAll(Arrays.asList(columns));
        return getSelf();
    }

    private SQLStatement sql() {
        return sql;
    }

    public <A extends Appendable> A usingAppender(A a) {
        sql().sql(a);
        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sql().sql(sb);
        return sb.toString();
    }

    private static class SafeAppendable {
        private final Appendable a;
        private boolean empty = true;

        public SafeAppendable(Appendable a) {
            super();
            this.a = a;
        }

        public SafeAppendable append(CharSequence s) {
            try {
                if (empty && s.length() > 0) {
                    empty = false;
                }
                a.append(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public boolean isEmpty() {
            return empty;
        }

    }

    private static class SQLStatement {

        public enum StatementType {
            DELETE, INSERT, SELECT, UPDATE
        }

        StatementType statementType;
        List<String> sets = new ArrayList<String>();
        List<String> select = new ArrayList<String>();
        List<String> tables = new ArrayList<String>();
        List<String> join = new ArrayList<String>();
        List<String> innerJoin = new ArrayList<String>();
        List<String> outerJoin = new ArrayList<String>();
        List<String> leftOuterJoin = new ArrayList<String>();
        List<String> rightOuterJoin = new ArrayList<String>();
        List<String> where = new ArrayList<String>();
        List<String> having = new ArrayList<String>();
        List<String> groupBy = new ArrayList<String>();
        List<String> orderBy = new ArrayList<String>();
        List<String> lastList = new ArrayList<String>();
        List<String> columns = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        boolean distinct;

        public SQLStatement() {
            // Prevent Synthetic Access
        }

        private void sqlClause(SafeAppendable builder, String keyword, List<String> parts, String open, String close,
                               String conjunction) {
            if (!parts.isEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append("\n");
                }
                builder.append(keyword);
                builder.append(" ");
                builder.append(open);
                String last = "________";
                for (int i = 0, n = parts.size(); i < n; i++) {
                    String part = parts.get(i);
                    if (i > 0 && !part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
                        builder.append(conjunction);
                    }
                    builder.append(part);
                    last = part;
                }
                builder.append(close);
            }
        }

        private String selectSQL(SafeAppendable builder) {
            if (distinct) {
                sqlClause(builder, "SELECT DISTINCT", select, "", "", ", ");
            } else {
                sqlClause(builder, "SELECT", select, "", "", ", ");
            }

            sqlClause(builder, "FROM", tables, "", "", ", ");
            sqlClause(builder, "JOIN", join, "", "", "\nJOIN ");
            sqlClause(builder, "INNER JOIN", innerJoin, "", "", "\nINNER JOIN ");
            sqlClause(builder, "OUTER JOIN", outerJoin, "", "", "\nOUTER JOIN ");
            sqlClause(builder, "LEFT OUTER JOIN", leftOuterJoin, "", "", "\nLEFT OUTER JOIN ");
            sqlClause(builder, "RIGHT OUTER JOIN", rightOuterJoin, "", "", "\nRIGHT OUTER JOIN ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            sqlClause(builder, "GROUP BY", groupBy, "", "", ", ");
            sqlClause(builder, "HAVING", having, "(", ")", " AND ");
            sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
            return builder.toString();
        }

        private String insertSQL(SafeAppendable builder) {
            sqlClause(builder, "INSERT INTO", tables, "", "", "");
            sqlClause(builder, "", columns, "(", ")", ", ");
            sqlClause(builder, "VALUES", values, "(", ")", ", ");
            return builder.toString();
        }

        private String deleteSQL(SafeAppendable builder) {
            sqlClause(builder, "DELETE FROM", tables, "", "", "");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        private String updateSQL(SafeAppendable builder) {

            sqlClause(builder, "UPDATE", tables, "", "", "");
            sqlClause(builder, "SET", sets, "", "", ", ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        public String sql(Appendable a) {
            SafeAppendable builder = new SafeAppendable(a);
            if (statementType == null) {
                return null;
            }

            String answer;

            switch (statementType) {
                case DELETE:
                    answer = deleteSQL(builder);
                    break;

                case INSERT:
                    answer = insertSQL(builder);
                    break;

                case SELECT:
                    answer = selectSQL(builder);
                    break;

                case UPDATE:
                    answer = updateSQL(builder);
                    break;

                default:
                    answer = null;
            }

            return answer;
        }
    }
}

set @db_name='car';
set @table_name='check';
SELECT
    COLUMN_NAME 字段名,
    COLUMN_TYPE 数据类型,
    IF
        (
                COLUMN_NAME IN ( SELECT COLUMN_NAME FROM information_schema.KEY_COLUMN_USAGE AS T WHERE T.table_schema = @db_name AND T.table_name = @table_name ),
                'PRIMARY KEY',
                IF
                    ( IS_NULLABLE = 'NO', 'NOT NULL', 'NULL' )
        ) AS `索引`,
    COLUMN_COMMENT 字段含义
FROM
    INFORMATION_SCHEMA.COLUMNS
WHERE
        table_schema = @db_name
  AND table_name = @table_name;

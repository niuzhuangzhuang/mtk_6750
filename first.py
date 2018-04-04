#!/usr/bin/python
#coding=utf-8

import MySQLdb

table = "one"

conn=MySQLdb.connect(host='localhost',user='root',passwd='123456',db='test')
cursor = conn.cursor()
cursor.execute ("SELECT VERSION()")
#cursor.execute('create database test1')
sql = """CREATE TABLE %s (
        FIRST_NAME  CHAR(20) NOT NULL,
        LAST_NAME  CHAR(20),
        AGE INT,  
        SEX CHAR(1),
        INCOME FLOAT )""" % table
#cursor.execute(sql)

insert = """insert into %s (FIRST_NAME, LAST_NAME, AGE, SEX, INCOME) values ('Tom', 'Jon', 12, 'M', 232)""" % table
select = "select * from %s" % table
delete = "delete from %s where age > '%d'" % (table, 20)
update = "update %s set age = age + 12 where FIRST_NAME = %s" % (table, "c")
try:
#cursor.execute(insert)
#conn.commit()

    cursor.execute(select)
    results = cursor.fetchall()
    for row in results:
        fname = row[0]
        lname = row[1]
        age = row[2]
        sex = row[3]
        income = row[4]

    print '%s, %s, %d, %s, %d' % (fname, lname, age, sex, income)
except:
    conn.rollback()
    print "Error: unable to fecth data"
cursor.close()
conn.close()

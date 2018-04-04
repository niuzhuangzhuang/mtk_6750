#!/usr/bin/python
#coding=utf-8

import socket 
import json
import sqlite3

host = '20.1.1.106'#为空代表为本地host 
#hostname = socket.gethostname() 
#print "name = " + hostname
#hostip = getipaddrs(hostname) 
print('host ip', host)#应该显示为：127.0.1.1 
port = 9999     # Arbitrary non-privileged port 

table_name = 'user'
key_name = 'name'
key_password = 'password'

def getipaddrs(hostname):#只是为了显示IP，仅仅测试一下 
    result = socket.getaddrinfo(hostname, None, 0, socket.SOCK_STREAM) 
    return [x[4][0] for x in result] 

def accept_json(conn, strs):
    data = json.loads(strs)
    name = data['name']
    print "name = " + name
    password = data['password']
    print "password = " + password
    select_sqlite(conn, name, password)

def create_sqlite():
    create = "create table if not exists %s (id integer primary key autoincrement, %s varchar(20), %s varchar(20))" % (table_name, key_name, key_password)
    print "create = " + create
    conn = sqlite3.connect('user.db')
    cursor = conn.cursor()
    cursor.execute(create)
    cursor.close()
    conn.commit()
    conn.close()

def inset_sqlite(name, password):
    insert = "insert into %s (name, password) values ('%s', '%s')" % (table_name, name, password)
    conn = sqlite3.connect('user.db')
    cursor = conn.cursor()
    cursor.execute(insert)
    cursor.close()
    conn.commit()
    conn.close()

def select_sqlite(addr, name, password):
    select = "select * from %s where name = '%s' and password = '%s'" % (table_name, name, password)
    conn = sqlite3.connect('user.db')
    cursor = conn.cursor()
    cursor.execute(select)
    result = cursor.fetchone()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(addr)
    if result is None:
        print "账号密码不对"
        soc.send('账号密码不对')
    else:
        print "登录成功"
        soc.send('登录成功')
    cursor.close()
    conn.close()

if __name__ == '__main__':
    create_sqlite()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host, port))
    s.listen(5)
    while True:
        conn, addr = s.accept()
        print('Connected by', addr)
        conn.send('Welcome')
        data = conn.recv(1024)
        print 'data = ' + data
#accept_json(addr, data)
        conn.send('Hello, %s!' % data)
        conn.close()


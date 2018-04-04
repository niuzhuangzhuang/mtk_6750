#! /usr/bin/python
#coding=utf-8

import socket
import threading
import time

def tcplink(sock, addrs):
    print 'Accept new connection from %s:%s...' % addrs
    sock.send('Welcome')
    while True:
        data = sock.recv(1024)
        time.sleep(1)
        if data == 'exit' or not data:
            break
        sock.send('Hello, %s!' % data)
    sock.close()
    print 'Connection from %s:%s closed.' % addrs

if __name__ == '__main__':
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(('20.1.1.106', 9999))
    s.listen(5)
    print 'Waiting for connection...'
    while True:
        sock, addrs = s.accept()
        t = threading.Thread(target=tcplink, args=(sock, addrs))
        t.start()

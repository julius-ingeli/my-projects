"""
Exercise 1: Change the socket program socket1.py to prompt the user
for the URL so it can read any web page. You can use split('/') to
break the URL into its component parts so you can extract the host
name for the socket connect call. Add error checking using try and
except to handle the condition where the user enters an improperly
formatted or non-existent URL.
"""
import socket

try:
    url = input("Enter URL: ")
    host = url.split("/")[2]

    mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    mysock.connect((host, 80))
    cmd = 'GET ' + url + ' HTTP/1.0\r\n\r\n'
    cmd = cmd.encode()
    mysock.send(cmd)

    while True:
        data = mysock.recv(512)
        if len(data) < 1:
            break
        print(data.decode(),end='')

    mysock.close()
except:
    print("The URL is improperly formatted or non-existent!")

""""
Exercise 2: Change your socket program so that it counts the number
of characters it has received and stops displaying any text after it has
shown 3000 characters. The program should retrieve the entire docu-
ment and count the total number of characters and display the count
of the number of characters at the end of the document.
"""
import socket

try:
    url = input("Enter URL: ")
    host = url.split("/")[2]

    mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    mysock.connect((host, 80))
    cmd = 'GET ' + url + ' HTTP/1.0\r\n\r\n'
    cmd = cmd.encode()
    mysock.send(cmd)

    received = b""
    while True:
        data = mysock.recv(512)
        if len(data) < 1:
            break
        received += data

    received = received.decode()
    print(received[:3000])
    print(len(received))

    mysock.close()
except:
    print("The URL is improperly formatted or non-existent!")
"""
Exercise 3: Use urllib to replicate the previous exercise of (1) retrieving
the document from a URL, (2) displaying up to 3000 characters, and
(3) counting the overall number of characters in the document. Don’t
worry about the headers for this exercise, simply show the first 3000
characters of the document contents.
"""
import urllib.request

url = input("Enter URL: ")
content = urllib.request.urlopen(url).read()
content = content.decode()

print(content[:3000])
print(len(content))
"""
Exercise 4: Change the urllinks.py program to extract and count para-
graph (p) tags from the retrieved HTML document and display the
count of the paragraphs as the output of your program. Do not display
the paragraph text, only count them. Test your program on several
small web pages as well as some larger web pages.
"""
import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter - ')
html = urllib.request.urlopen(url, context=ctx).read()
soup = BeautifulSoup(html, 'html.parser')

# Retrieve all of the paragraph tags
tags = soup('p')
print(len(tags))


"""
Exercise 5: (Advanced) Change the socket program so that it only shows
data after the headers and a blank line have been received. Remember
that recv receives characters (newlines and all), not lines.
"""
import socket

try:
    url = input("Enter URL: ")
    host = url.split("/")[2]

    mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    mysock.connect((host, 80))
    cmd = 'GET ' + url + ' HTTP/1.0\r\n\r\n'
    cmd = cmd.encode()
    mysock.send(cmd)

    received = b""
    while True:
        data = mysock.recv(512)
        if len(data) < 1:
            break
        received += data
    pos = received.find(b"\r\n\r\n")
    content = received[pos+4:].decode()
    print(content)
    mysock.close()
except:
    print("The URL is improperly formatted or non-existent!")
# Simple-HTTP-Server
This is a simple HTTP server written in java to parse incoming HTTP requests.

The project consists of four main parts:

1.HttpServer - Creates a Httpserver class instance to handle incoming requests using sockets. It uses concurrency(implemented with multi-threading) to handle incoming requests from the different clients and handle multiple requests from the same client.
2.HttpParser - It parses a HTTP request received by the server. Parsing is handled in accordance to [RFC 7230](https://datatracker.ietf.org/doc/html/rfc7230), [RFC 7231](https://datatracker.ietf.org/doc/html/rfc7231), [RFC 7232](https://datatracker.ietf.org/doc/html/rfc7232)
3.ConfigurationManager - Sets up our port and webroot where our server operates on
4.JsonParser - Parses the config.json file using jackson api

# Dependencies:
1. JDK - 11
2. Apache Maven 3.8.7

# Testing:
Unit testing has been implemented for one method parseRequest of HttpParser using JUnit 4.

# Notes:
1. Currently the project can only read simple HTTP/1.1 requests and irrespective of method serves the same html file.
2. Test coverage is very low and has to be improved.

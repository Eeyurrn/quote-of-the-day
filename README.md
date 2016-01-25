# quote-of-the-day

App which generates a quote of the day and tells every 7th visitor that they have won with a link to www.performancecentre.com

This application was built in the Clojure progamming language using Compojure for web-request routing.

Clojure is Lisp based off the JVM, it is a functional programming language with a strong focus on immutability and first-class functions (as well as a ton of parenthesis).

[Clojure Website](http://clojure.org/)

[Compojure](https://github.com/weavejester/compojure)

---------------

## Prerequisites

Java 1.6 or higher must be installed [Download Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

You will need [Leiningen][] 2.0.0 or above installed.


[leiningen]: https://github.com/technomancy/leiningen

There are instructions in that link, please see the **Installation** section for instructions on how to install the Leiningen build tool.

*Note: Clojure will be installed along with Leiningen*


---------------
## Running

To start a web server for the application, run:

    lein ring server-headless

The application can be accessed from `http://127.0.0.1:3000/`

Source code
-------------

Go to `src/quote-of-the-day/handler.clj` to view the server source
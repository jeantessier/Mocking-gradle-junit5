# Mocking in Java: jMock vs. EasyMock vs. Mockito

by [Jean Tessier](https://jeantessier.com/)

This document shows how to do common mocking tasks in Java using both
[jMock](http://jmock.org/),
[EasyMock](https://easymock.org/),
and [Mockito](https://easymock.org/).

Throughout, I use the terminology defined by Gerard Meszaros in his book
[xUnit Test Patterns](http://www.amazon.com/xUnit-Test-Patterns-Refactoring-Addison-Wesley/dp/0131495054/ref%3Dpd%5Fbbs%5Fsr%5F1/104-7143783-6750325?ie%3DUTF8&s%3Dbooks&qid%3D1188541102&sr%3D8-1).

All example were tested with JUnit 3.8.2, jMock 2.12, EasyMock 4.3, and Mockito
3.3.3 using Java 8.

#### DISCLAIMER (2008)

I like jMock a lot more than I like EasyMock.  The DSL for specifying
expectations in jMock takes some getting used to, but it is more expressive
than the one in EasyMock.  And while I don't like anonymous inner classes, I
like static imports even less.  :-)

#### DISCLAIMER (2020)

A Slant article titled
["What are the best mock frameworks for Java"](https://www.slant.co/topics/259/~best-mock-frameworks-for-java),
in 2020, had a top three of:

1. Mockito
1. JMockIt
1. EasyMock (a very distant third)

There has been very little development, of late, on either JMock or EasyMock.
In the meantime, Mockito has been on a tear.  I don't know JMockIt.

For my part, my new favorite testing framework on the JVM is
[Spock](http://spockframework.org/).  It uses Groovy to write the tests, which
have a much more BDD feel to them.  It's support for mocking is also top notch.

## Introduction

Mocking allows you to isolate a class or method and test it in isolation.  You
replace all of its collaborator with _mocks_ that essentially simulate the
normal environment of the _SUT_ (_System Under Test_).  Mocks replace the SUT's
_DOCs_ (_Depended-On Components_) and give you fine control on how the SUT
interacts with its environment and what messages it gets back from it.

#### jMock ([jmock.org](http://jmock.org/))

jMock focuses on explicitly specifying the behavior of the mocks using a
specialized _DSL_ (_Domain-Specific Language_) embeded in the Java code.  The
notation takes some getting used to, but it makes the specification of behavior
stand out in the test code.

#### EasyMock ([easymock.org](https://easymock.org/))

EasyMock takes a record/replay approach.  You first train the mock by making
the expected method calls on it yourself.  You then switch the mock into
replay-mode before exercising the SUT.  Specifying the behavior is just regular
method calls on a typed Java object.

#### Mockito ([mockito.org](https://site.mockito.org/))

Mockito takes a different approach.  Whereas jMock and EasyMock try to specify
the behavior fully beforehand, Mockito simply records everything
indiscriminately and then lets you verify that what you wanted actually
happened.  Instead of a large specification section at the front of the test,
you have a large verification section at the end of it.  Strictly speaking,
Mockito uses test spies rather than mocks to simulate the DOCs.

## Comparison

> Once I figure out how to do a decent three-column layout with code samples in
> MarkDown, I will port the remainder of the article here.

----

Date | Edit
---- | ----
2008-05-29 | First draft.
2008-11-20 | Last substantial edit.
2020-04-29 | Making sure it all still works with the latest versions.

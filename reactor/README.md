### Reactive Stream

Reactive programming offers a solution for:  
1. How do we Handle more users?  
2. How do we scale?  
3. How do we make more efficient use of our threads?

The purpose of Reactive Streams is to provide a standard for asynchronous stream processing with non-blocking backpressure.  
The reactive streams specification consists of four interfaces and a single class.

#### Publisher

```java
public interface Publisher<T> {
    public void subscribe(Subscriber<? super T> s);
}
```  
Publishes or broadcasts data (of type `T`) to a `Subscriber<T>`

#### Subscriber

```java
public interface Subscriber<T> {
    public void onSubscribe(Subscription s);
    public void onNext(T t);
    public void onError(Throwable t);
    public void onComplete();
}
```  
As soon as a `Subscriber<T>` subscribes, it receives a `Subscription`.

#### Subscription

```java
public interface Subscription {
    public void request(long n);
    public void cancel();
}
```  
The most crucial class from the whole reactive stream specification.  
Is a link between the producer (`Publisher<T>`) and the consumer (`Subscriber<T>`).  
The `Subscriber<T>` uses the `Subscription` to request more data with the `request(long n)`. The subscriber control the flow of data (the rate of processing).

#### Processor

```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {

}
```  
It's just a bridge, implementing both `Publisher<T>` and `Subscriber<T>`. It's a producer and a consumer.

#### org.reactivestreams.FlowAdapters

Is a concrete class that help us adapt reactive stream types interchangeably to and from the Java 9 `Flow` analogs.

#### Flux<T> and Mono<T>

Reactor provides two specializations of `Publisher<T>`  
1. `Flux<T>`, provide zero or more values. It's unbounded.  
2. `Mono<T>`, provide zero or one values.

The recommendation is use `Publisher<T>` as an argument and return a `Flux<T>` or `Mono<T>`.  
Our client decide what kind of data given.

The best part of reactive programming is that it gives us one kinda "stuff", a uniform interface for dealing with asynchronous streams in an asynchronous world.  
The challenge is how to adapt the real world's asynchronous events into the requisite `Publisher<T>` interface.  
- How do you take events from a Spring Integration inbound adapter and turn it into stream?  
- How do you take data emitted from an existing threaded application and process them as a reactive stream?

#### Operators

Each `Publisher<T>` is immutable, operators create new `Publisher<T>`.

##### `transform`

What if we want to operate on existing publisher? Use `transform`, it give use reference to the current publisher.  
It gives us a chance to change the publisher at assembly time, on initialization.

##### Do this and then that

In asynchronous and reactive world we don't have guarantees about the order of process.  
If we want same behavior with non-asynchronous programming, use `thenMany`

##### `map`

This function modifies each item by the source `Publisher<T>` and emits the modified item.  
The source stream replaced by another stream with value is output from the `map`

##### `flatMap` and `concatMap`

What if we want to take each item from publisher then call another service that return a `Publisher<T>`?  
Using `map` we will get Publisher<Publisher<T>> return type.

`flatMap` and `concatMap` merge items emitted by inner streams into the outer stream.  
The difference between them is that the order in which the item arrive. `flatMap` interleaves items, the order may be different.  
`concatMap` preserves the order of items. The price is we have to wait for each Publisher<T> to complete its work. We lose asynchronicity.

Use case for `concatMap` is event processing. Our message represent state mutation, "read"-"update"-"read"-"delete"-"delete".  
We want to proceed the message in the same order.

##### `switchMap`

`switchMap` cancels any outstanding inner publishers as soon as a new value arrives.  
The use case is autocomplete. Our typing speed faster than network call. `switchMap` will cancel previous incomplete network calls.

##### `take` and `filter`

We use `take(long)` to limit the number of elements.  
If we want to apply some predicate and stop consuming message when the predicate matches, use `takeUntil(Predicate)`  
Other variant is `take(Duration)`

We can selectively filter out some values with `filter`.  
This operator will discard the values that doesn't match the predicate.

##### doOn* callback

Use when we want to peek into the stream.

##### Taking control of your stream's destiny

Control for more complex logic using `handle`

### Read more

[reactive-streams-jvm](https://github.com/reactive-streams/reactive-streams-jvm)
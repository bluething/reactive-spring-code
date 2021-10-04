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

### Read more

[reactive-streams-jvm](https://github.com/reactive-streams/reactive-streams-jvm)
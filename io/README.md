### Problem with synchronous read

What if the network slow or down? Our InputStream may take a long time to finished or never return.  
We can spin other thread (scale) but there are limit on how much thread can be created (it depends on our operating system).

See more about [thread problem](https://github.com/bluething/learnjava/tree/main/threaddump).

### Read more

[Understanding CPU and I/O bound for asynchronous operations](https://www.hellsoft.se/understanding-cpu-and-i-o-bound-for-asynchronous-operations/)  
[Don’t block your threads with IO bound operations](https://medium.com/@victor.borza/dont-block-your-threads-with-io-bound-operations-e854d52223ea)  
[Scaling Up IO Tasks in Java](https://medium.com/swlh/scaling-up-io-tasks-795df1e29d7e)  
[Distinguish CPU-Bound work from IO-bound work](https://channel9.msdn.com/Series/Three-Essential-Tips-for-Async/Tip-2-Distinguish-CPU-Bound-work-from-IO-bound-work)
### Problem with synchronous read

What if the network slow or down? Our InputStream may take a long time to finished or never return.  
We can spin other thread (scale) but there are limit on how much thread can be created (it depends on our operating system).

See more about [thread problem](https://github.com/bluething/learnjava/tree/main/threaddump).
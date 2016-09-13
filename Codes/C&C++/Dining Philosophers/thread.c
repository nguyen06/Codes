#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>

void *func(int n);
pthread_t philosopher[5];
pthread_mutex_t chopstick[5];

int main()
{
     int i;
     void *msg;
     for(i=1;i<=5;i++)
     {
          /*
           * the function initialises the mutex referenced by 
           * &chopstck[i] with attributes specified by NULL, so 
           * default attribute are used. Upone successful inialisation,
           * the state of the mutex become initialised and unclocked

           * What is a muxtex?
           * A muxtex is a program object that allows multiple program threads
           * to share the same resource, such as file access but nor not simultaneously,
           *. When a program is started, a mutex is created with a unique name
           */
           printf("aaaaa \n");
          pthread_mutex_init(&chopstick[i],NULL);
     }
     for(i=1;i<=5;i++)
     {
          /*
           * the function create a new thread, the default attribute is use
           * The thread is created executing func with i as its argument. 
           * 
           * func()
           * 
           */
           printf("bbbbb \n");
          pthread_create(&philosopher[i],NULL,(void *)func,(int *)i);
     }
     for(i=1;i<=5;i++)
     {
          /*
           * the pthread_join() function shall execution of the calling thread
           * untill the target thread terminates. When return a successful pthread_join()
           * call with a non-NULL &msg argument, the value passed to pthread_exit()
           * by the terminating thread shall return be made available in the location 
           * referenced by &msg
           *
           * After the target (thread 1, and 3 terminated), the thread 2, 4,5 available
           * to execute, So we can see philosopher 2, 4, 5 are eating at the same time
           * which is uncorrect. 
           */
           printf("ccccc \n");
          pthread_join(philosopher[i],&msg);
     }
      for(i=1;i<=5;i++)
      {
          printf("ddddd \n");
          /*
           * The pthread_mutex_destroy() function destroy the mutex referenced
           * by &chopstick[i], the mutex object become unitialized. 
           */
          pthread_mutex_destroy(&chopstick[i]);
      }
     return 0;
}

void *func(int n)
{
    /* in the thread is created above, printout the thread number
     * which is the Philosopher number, At this time all the 5 thread
     * are create at the same time, very close time, 
     */
     printf ("\nPhilosopher %d is thinking ",n);
     /*
      * The mutex object &chopstick[n] should be clocked by a call to
      * pthread_mutex_lock() that return zero or [EOWNERDEAD]. If the 
      * mutex is already locked by another thread, the calling thread 
      * shall block until the mutex become available. 
      * the thread 1 and 3 always are executed first 
      */
     pthread_mutex_lock(&chopstick[n]);//when philosopher 5 is eating he takes fork 1 and fork 5
     pthread_mutex_lock(&chopstick[(n+1)%5]);
     printf ("\nPhilosopher %d is eating ",n);
     sleep(3);
     /* after sleeping for 3 second, the pthread_mutex_unlock() function is called
      * release the mutex &chopstick[n] object. The manner in which a mutex 
      * is released depending upon the mutex's type attribute. Resulting the 
      * mutex becoming available, the scheduling policy shall determine which thread shall
      * acqurie the mutex
      */
     pthread_mutex_unlock(&chopstick[n]);
     pthread_mutex_unlock(&chopstick[(n+1)%5]);
     printf ("\nPhilosopher %d finished eating ",n);
}
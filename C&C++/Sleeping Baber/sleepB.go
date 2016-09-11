package main

import "fmt"

func customer(id int, shop chan<- int) {
    // Enter shop if seats available, otherwise leave
    //fmt.Println("Uncomment this line and the program works")
    if len(shop) < cap(shop) {
        shop <- id
    }
}

func barber(shop <-chan int) {
    // Cut hair of anyone who enters the shop
    for {
        fmt.Println("Barber cuts hair of customer", <-shop)
        //sleep(5);
    }
}

func main() {
    shop := make(chan int, 4) // five seats available

    // go statement start execute the function call as an independent concurrent
    // thread of control, or goroutine within the same address space
    // the function barber is avaluated as usual in the calling goroutine, but 
    // unlike with a reguler call, program execution does not wait for the invoked
    // function to complete. instead, the function begin execting independtly 
    // in a new goroutine. When the function terminates, its goroutine also terminates
    // if the function has return values, they are discarded when the function completed

    /* 
     this implementation, the barber go and take customer to cutting room
     customer does not wake the barber up

     the customers keep coming, and there is one sit available. they might coflict
     , the barber can not get any customer
    */
    go barber(shop)

    fmt.Println("Hello");
    for i := 0; ; i++ {
        //sleep(4);
        //fmt.Println(i);

        customer(i, shop)
        //sleep(4);
    }
    //fmt.Println("Uncomment this line and the program works")

}

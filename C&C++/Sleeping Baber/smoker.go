/*
	there are three smokers and one agent. A cigarette is made of three
	ingredient: tobacco, paper, and match. each smoker has infinite supply 
	of one ingrident. The agent has infinite supply of all ingredients. The 
	agent repeadly choose two ingredient at random and puts them on the table
	and the smoker who has the complementary ingredient can take two ingredent
	and make a cigarette and smoke. in this problem, the agent represents the 
	operating system and the smoker represent the process. the operating system
	should allocate the required resource to the process and at the same time, 
	avoid deadlock. 

	deadlock!
	image that the agent puts out tobaco and paper. Since the smoker with matches 
	is waiting for tobacco, it might not be clocked. And the smoker with tobacco 
	is waiting for paper. So it is possible that it may not also not be blocked.
	Then the first thread will block on paper and the second will block on match
	that is a deadlock!

	the smoker with the tobacco takes the paper, and the can not smoke. deadlock
	similarly, the smoker with the paper get tabacco. He can not smoke, that dealock

	There are to retrictions on the solution ( Patil's version)

	1. The agent code cannot be modified

	2. Conditional statements and arrays of semaphores are not allowed

	with this restriction, this problem is unsolvable. The Patil's version is 
	also called the impossible version

	However, while the first restriction makes sence because the operating system's
	code should not be modified every time we need to solve a problem. The second
	restriction is not reasonable, according to David Parnas, he called such retriction
	"artificail" and said that artficial retriction make many problem unsolvable

	The solution proposes by Parnas uses three helper threads called "pishers" 
	that respond to the signals from the agent, keep track of the available 
	ingredents, and signals the approciate smoker.

	The additional variable and semaphores are 
	1. isTobacco = isPaper = isMatch = false
	2. tobaccoSem = Semaphore(0)
	3. paperSem = Semphore(0)
	4. matchSem = Semaphore(0)

	the boolean variable indicate whether or nor an ingedent is on the table. The
	pusher use tobacco to signal the smoker with tobacco and other semaphore likewise

	the pusher wakes up any time there is tabacoo on the table. if it finds isPaper
	is true, it knows that Pusher B has already ran, so it can signal the smoker
	with matches. Similarly, if it finds a match on the table, it can signal the smoker
	with paper. but if pusher A run first, then it will find both isPaper and isPath
	false. It cannot signal any of the smokers, so it sets isTobacco true. The other
	pushers are similar. Since the pusher do all the real work, the smoker code is trivial



 */


package main

import (
	"fmt"
	"time"
)

type Tobacco struct{}
type Paper struct{}
type Match struct{}
type Cigarette struct{}

type Agent struct {
	Name        string
	TobaccoCh   chan<- Tobacco
	PaperCh     chan<- Paper
	MatchCh     chan<- Match
	CigaretteCh <-chan Cigarette
}
/*
	First, as suggested by Patil, there are three agents producing pairs of 
	ingredients. The agent waits for a cigarette to be produced before 
	providing the ingredients for the next one. Since there are three agents, 
	his is used to select which one of them is going to go next at producing 
	ingredients. Each produced ingredient is sent over its corresponding channel.
	 select is used to avoid imposing an order on the sending process. In order 
	 to avoid having to write code for an agent that produces each ingredient pair,
	  one of the channels is nil for each of the three agents (remember that a send
	  o a nil channel blocks forever). Whenever an ingredient is sent, the corresponding 
	  channel is set to nil.
*/
func (a *Agent) run() {
	for {
		<-a.CigaretteCh

		fmt.Println(a.Name)

		tobaccoCh := a.TobaccoCh
		paperCh := a.PaperCh
		matchCh := a.MatchCh

		for i := 2; i > 0; i-- {
			select {
			case tobaccoCh <- Tobacco{}:
				fmt.Println(a.Name, "Tobacco")
				tobaccoCh = nil
			case paperCh <- Paper{}:
				fmt.Println(a.Name, "Paper")
				paperCh = nil
			case matchCh <- Match{}:
				fmt.Println(a.Name, "Match")
				matchCh = nil
			}
		}
	}
}

type SmokerPackage struct {
	Tobacco *Tobacco
	Paper   *Paper
	Match   *Match
}
/*
Next there’s a “pusher”, which takes ingredients from the agent and pushes 
them to the smokers. In the same way as done by Parnas, the pusher keeps track of 
the destination smoker by way of having a bit field that describes whether or
not the ingredients should be sent or if it should keep waiting for more. 
Because of the way the agent is constructed, it’s not necessary to set 
channels to nil after receiving an item from them, as we know it’s going to
 send exactly two of them.

*/
type Pusher struct {
	TobaccoCh <-chan Tobacco
	PaperCh   <-chan Paper
	MatchCh   <-chan Match

	SmokerTobaccoCh chan<- SmokerPackage
	SmokerPaperCh   chan<- SmokerPackage
	SmokerMatchCh   chan<- SmokerPackage
}

func (p *Pusher) run() {
	smokers := []chan<- SmokerPackage{
		nil,               // 0
		nil,               // 1
		nil,               // 2
		p.SmokerMatchCh,   // 3
		nil,               // 4
		p.SmokerPaperCh,   // 5
		p.SmokerTobaccoCh, // 6
	}

	pkg := SmokerPackage{}

	t := 0

	for {
		select {
		case item := <-p.TobaccoCh:
			fmt.Println("Pusher", "Tobacco")
			pkg.Tobacco = &item
			t |= 0x1
		case item := <-p.PaperCh:
			fmt.Println("Pusher", "Paper")
			pkg.Paper = &item
			t |= 0x2
		case item := <-p.MatchCh:
			fmt.Println("Pusher", "Match")
			pkg.Match = &item
			t |= 0x4
		case smokers[t] <- pkg:
			fmt.Println("Pusher", t)
			t = 0
			pkg = SmokerPackage{}
		}
	}
}
/*
And lastly, the smoker is trivial: receive a package with ingredients, 
build a cigarette, and send it over the channel. In this case, that channel 
is read by the agent to proceed with the next cycle.
*/
type Smoker struct {
	Name        string
	PackageCh   <-chan SmokerPackage
	CigaretteCh chan<- Cigarette
}

func (s *Smoker) run() {
	for {
		pkg := <-s.PackageCh
		fmt.Println(s.Name, pkg)
		s.CigaretteCh <- Cigarette{}
	}
}

func main() {
	var (
		tobaccoCh       = make(chan Tobacco)
		paperCh         = make(chan Paper)
		matchCh         = make(chan Match)
		smokerTobaccoCh = make(chan SmokerPackage)
		smokerPaperCh   = make(chan SmokerPackage)
		smokerMatchCh   = make(chan SmokerPackage)
		cigaretteCh     = make(chan Cigarette)
	)

	for i := 3; i > 0; i-- {
		agent := Agent{
			Name:        fmt.Sprint("Agent ", i),
			TobaccoCh:   tobaccoCh,
			PaperCh:     paperCh,
			MatchCh:     matchCh,
			CigaretteCh: cigaretteCh,
		}

		switch i {
		case 1:
			agent.TobaccoCh = nil
		case 2:
			agent.PaperCh = nil
		case 3:
			agent.MatchCh = nil
		}

		go agent.run()
	}

	pusher := Pusher{
		TobaccoCh:       tobaccoCh,
		PaperCh:         paperCh,
		MatchCh:         matchCh,
		SmokerTobaccoCh: smokerTobaccoCh,
		SmokerPaperCh:   smokerPaperCh,
		SmokerMatchCh:   smokerMatchCh,
	}

	go pusher.run()

	for i := 3; i > 0; i-- {
		smoker := Smoker{
			Name:        fmt.Sprint("Smoker ", i),
			CigaretteCh: cigaretteCh,
		}

		switch i {
		case 1:
			smoker.PackageCh = smokerTobaccoCh
		case 2:
			smoker.PackageCh = smokerPaperCh
		case 3:
			smoker.PackageCh = smokerMatchCh
		}

		go smoker.run()
	}

	cigaretteCh <- Cigarette{}

	time.Sleep(2 * time.Second)
}
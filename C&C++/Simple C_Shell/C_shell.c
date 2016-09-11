#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define RL_Max_Size 1024;
/*
 * we use the while loop to read charaters in the given line. 
 * We read a character and store it an int because when we hit EOF, 
 * which is a integer, not character. So we need to use int to check 
 * this case. if it is a newline or EOF, we null terminate our current 
 * string and return it. otherwise, we add the character to out existing 
 * string
 */
char *readLine()
{
	int max_Size = RL_Max_Size;
	// create a arry for the string 
	char *array = malloc(sizeof(char) * max_Size);
	// create a position variable for string array
	int pos = 0;
	int ch;

	// check if the memmory still available for the buffer array
	if(!array){
		fprintf(stderr,"Allocation error\n");
		exit(EXIT_FAILURE);
	}

	// read a character in the in put string
	while(1){
		ch = getchar();

		// if we hit the EOF or new line, we null terminate our current 
		// string and return it
		if(ch == EOF || ch == '\n' ){
			array[pos] = '\0';
			return array;

		}else{
			array[pos] = ch;
		}
		pos++;

		// if we have exceeded the array, we need to reallocate
		if(pos >= max_Size){
			max_Size += RL_Max_Size;
			// reallocate the array
			array = realloc(array, max_Size);

			// check if the memory still available for expanded array
			if(!array){
				fprintf(stderr,"Allocation error\n");
				exit(EXIT_FAILURE);
			}
		}
	}
}

/*
 * At this point, we have already read the line. Now, our job is to 
 * parsing the line into a list of arguments. We just use the whitespace
 * to separate arguments from each other. That mean we all need to "tokenize"
 * the string using whitespace as delimiters.
 *
 * char *strtok(char *str, const char *delim)
 *    str -- the content of this string are modified and bronken into smaller
 *            strings (tokens)
 *    delim -- this is the C string containing the delimiters, these may
 *             vary from one call to another
 */

#define number_tokens 64;
#define delimeters " \t\r\n\a"
char **parsingLine(char *line){

	// size of tokens
	int size = number_tokens; 

	// the position of token in the array_tokens
	int pos = 0;
	char **array_Tokens = malloc(sizeof(char*) * size);
	char *token;

	// check if the memmory still available at this area
	if(!array_Tokens){
		fprintf(stderr, "Allocation error\n");

		/* EXIT_FAILURE is the standard vaue for returning unsuccessful 
		 * termination. We can use either exit(0) or EXIT_SUCCESS  for
		 * success case
		 */ 
		exit(EXIT_FAILURE);
	}

	//call the strtok for getting the token
	token = strtok(line, delimeters);

	// when the token not null, push it in the array_Tokens
	while(token != NULL){
		array_Tokens[pos] = token;
		pos++;

		// if the number of tokens exceeded the number of tokens
		if(pos >= size){
			size += number_tokens;
			array_Tokens = realloc(array_Tokens, sizeof(char*) *size);

			// rechecking the memmory
			if(!array_Tokens)
			{
				fprintf(stderr, "Allocation error\n");

				/* EXIT_FAILURE is the standard vaue for returning unsuccessful 
			 	 * termination. We can use either exit(0) or EXIT_SUCCESS  for
			 	 * success case
			 	 */ 
				exit(EXIT_FAILURE);	
			}
		}
		token = strtok(NULL, delimeters);
	}
	// at the end of array add the NULL to the array_Tokens
	array_Tokens[pos] = NULL;
	return array_Tokens;

}

/*
 * At this point, we have array of tokens which is ready to execute
 * which the opcode, we can figure out when we need to change directory
 * and when we exit from the execution. 
 * When fork() function is called, the operating system makes a duplicate
 * of the process and start them both running. the original process is 
 * called the "parent", and the new one is called the "child"
 * 	+ if fork() return negative value, the creation of a child process was
 *    unsuccessful.
 *  + fork() returns a zero to the newly created child process
 *  + fork() returns a positive value, the process ID of the child process,
 *      to the parent. the return process ID is of type pid_t defined in sys/types.h
 *      Normally, the process ID is an integer. Moreover, a process can use
 *      function getid() to retrieve the process ID assigned to this process, but
 *      not in this case
 */

int execute(int opcode, char **args){

	switch(opcode){
		case 0: // skip empty command
			return 1;
		case 1: // exit the program
			return 0;
		case 2: // change the directory
			if(chdir(args[1]) != 0){
				perror(args[1]);
			}
			return 1;
		case 3:
		{
			pid_t pid, wpid;
  			int status;

			  pid = fork();
			  // the child process will take the first if condition where
			  // pid == 0
			  if (pid == 0) {
			    // Child process
			    /* execvp expects a program name and an array of string arguments
			     * the first one have to be a program name(v means). the 'p' means that 
			     * instead of providing the full file path of the program to run, we are
			     * going to give its name, and let the operating system search for the program
			     * in the path.
			     */

			    if (execvp(args[0], args) == -1) {
			      perror(args[0]); // to print a error message
			    }
			    exit(EXIT_FAILURE);
			  } else if (pid < 0) {
			    // Error forking
			    perror(args[0]);
			  } else {
			    /*Parent process, that means fork() execute successfully
			     * we know that the child is going to execute the process,
			     * so the parent needs to wait for for the command to finish 
			     * running. we use waitip() to wait for the process's state to 
			     * change  
			     */
				    pid_t curr_pid;
				    curr_pid = pid;
				    while(pid != wait(0));
				    curr_pid = (pid_t)0;
				 }

			  return 1;
		}

	}
}
void print_my_name()
{
	printf("\n");
	printf("Name: Ngoc Thinh Nguyen \n");
	printf("ID: V00817304 \n");
	printf("Reference: http://brennan.io/2015/01/16/write-a-shell-in-c/ \n");
	printf("\n");
}
int main(int argc, char **argv)
{
	char *line;
	char **args;
	int opcode;
	int status;
	print_my_name();
	do{
		printf("[My Shell ] ");
		line = readLine();
		args = parsingLine(line);

		/*
 		 * At this point, we have an array of tokens ready to execute
 		 * However, we need to parse the command line and get the operation code
 	     * code 0: Empty string    code 1: exit the shell    code 2: change directory
 		 * code 3: run cmd at cwd
		 */
		if(*args == NULL){
			opcode = 3;
		}else if(strcmp(*args, "exit") == 0){
			opcode = 1;
		}else if(strcmp(*args, "cd") == 0){
			opcode = 2;
		}else{
			opcode = 3;
		}
		
		//printf("%d\n",opcode);
		status = execute(opcode, args);
		free(line);
		free(args);
	}while(status);

	return EXIT_SUCCESS;
}
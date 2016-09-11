/*
 * NGUYEN NGOC THINH
 * V00817304
 * SENG 265
 * ASSIGNMENT 1
 */
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "string_set.h"

using namespace std;


string_set::string_set() {
    /*create a empty hash table, each element of the hash table
     * is the head of the link list
     */
    for(int i = 0; i < HASH_TABLE_SIZE - 1; i++) {
        hash_table[i] = NULL;
    }
    iterator_index = 0;
    iterator_node = hash_table[0];
    
}

void string_set::add(const char *s) {
    
    
    
    
    int position = hash_function(s);
    iterator_node = hash_table[position];
    
    if(contains(s) == 1)
        
    {
        throw duplicate_exception();
    }
    
    node *head = new node;
    head -> s = new char[strlen(s)+1];
    strcpy(head -> s , s);
    
    head->next = hash_table[position];
    hash_table[position]= head;
    //iterator_node = head;
    return;
}

void string_set::remove(const char *s) {
    
    int position = hash_function(s);
    
    
    
    if(contains(s) ==0)
    {
        throw not_found_exception();
    }
    
    node * pre = NULL;
    pre =hash_table[position];
    
    if(strcmp(hash_table[position] ->s, s) == 0)
    {
        
        if(pre -> next == NULL){
            hash_table[position] = NULL;
            
        }else{
            hash_table[position] = pre -> next;
            //iterator_node = hash_table[position];
            iterator_index =0;
        }
        return;
    }
    
    
}

int string_set::contains(const char *s) {
    int position = hash_function(s);
    
    node *p = hash_table[position];
    while(p != NULL)
    {
        
        if(strcmp(p ->s, s)==0)
        {
            
            return 1;
        }
        
        p = p->next;
    }
    
    return 0;
    
}

void string_set::reset() {
    
    for (int i = HASH_TABLE_SIZE -1 ; i >=0; --i)
    {
        if(hash_table[i]!=NULL)
        {
            iterator_index = i-1;
            iterator_node = hash_table[i];
            
            
        }
    }
    
    
}

const char *string_set::next() {
    
    
    node *curr;
    
    curr = iterator_node;
    if(curr!=NULL && curr->next!=NULL)
    {
        iterator_node = iterator_node->next;
        return iterator_node->s;
    }
    else
    {
        for (int i = iterator_index+1; i < HASH_TABLE_SIZE; ++i)
        {
            if(hash_table[i]!=NULL)
            {
                iterator_index = i;
                iterator_node = hash_table[i];
                return iterator_node->s;
            }
        }
    }
    
    return NULL;
}

string_set::~string_set() {
    node *temp;
    node *tempNext;
    for(int i =0 ; i < HASH_TABLE_SIZE; i++){
        temp = hash_table[i];
        while(temp != NULL)
        {
            tempNext = temp->next;
            delete temp;
            temp = tempNext;
        }
        hash_table[i] = NULL;
    }
}

int string_set::hash_function(const char *s) {
    /* this function is to map the string to a integer between 0
     * to HASH_TABLE_SIZE -1
     */
    int lenght = strlen(s);
    int sum = 0;
    for(int i = 0 ; i < lenght ; i++)
    {
        sum = sum + (int)s[i];
        
    }
    
    return sum % HASH_TABLE_SIZE; 
}